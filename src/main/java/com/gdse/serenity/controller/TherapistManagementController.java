package com.gdse.serenity.controller;

import com.gdse.serenity.bo.BOFactory;
import com.gdse.serenity.bo.custom.impl.TherapistBOImpl;
import com.gdse.serenity.dto.TherapistDTO;
import com.gdse.serenity.view.tdm.TherapistTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class TherapistManagementController implements Initializable {

    @FXML
    private Button addTherapistButton;

    @FXML
    private AnchorPane ancTherapist;

    @FXML
    private Button assignProgramsButton;

    @FXML
    private ListView<?> assignedProgramsListView;

    @FXML
    private Button clearButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button editButton;

    @FXML
    private TableColumn<TherapistTM, String> emailColumn;

    @FXML
    private ComboBox<String> filterComboBox;

    @FXML
    private TableColumn<TherapistTM, String> idColumn;

    @FXML
    private TableColumn<TherapistTM, String> nameColumn;

    @FXML
    private TableColumn<TherapistTM, String> phoneColumn;

    @FXML
    private Button saveButton;

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchField;

    @FXML
    private TableColumn<TherapistTM, String> specializationColumn;

    @FXML
    private TableColumn<TherapistTM, String> statusColumn;

    @FXML
    private ComboBox<String> statusComboBox;

    @FXML
    private Label statusLabel;

    @FXML
    private Label therapistIdLabel;

    @FXML
    private TableView<TherapistTM> therapistTable;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtSpecification;

    @FXML
    private Button viewScheduleButton;

    TherapistBOImpl therapistBO = (TherapistBOImpl) BOFactory.getInstance().getBO(BOFactory.BOType.THERAPIST);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("tId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        specializationColumn.setCellValueFactory(new PropertyValueFactory<>("specialization"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        try {
            refreshPage();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void refreshPage() throws SQLException, ClassNotFoundException {
        refreshTable();

        String nextID = therapistBO.getNextId();
        therapistIdLabel.setText(nextID);
        txtName.setText("");
        txtEmail.setText("");
        txtPhone.setText("");
        txtSpecification.setText("");
        statusComboBox.getSelectionModel().clearSelection();

        loadStatus();

        searchField.clear();

        saveButton.setDisable(true);
        deleteButton.setDisable(true);
        editButton.setDisable(true);

    }

    private void loadStatus() {
        statusComboBox.setItems(FXCollections.observableArrayList("Available", "Not Available"));
    }

    private void refreshTable() throws SQLException, ClassNotFoundException {
        List<TherapistDTO> therapists = therapistBO.getAll();  // Get all from the BO
        ObservableList<TherapistTM> therapistTMS = FXCollections.observableArrayList();

        for (TherapistDTO therapist : therapists) {
            TherapistTM therapistTM = new TherapistTM(
                    therapist.getTId(),
                    therapist.getName(),
                    therapist.getEmail(),
                    therapist.getPhone(),
                    therapist.getSpecialization(),
                    therapist.getStatus()
            );
            therapistTMS.add(therapistTM);
        }

        therapistTable.setItems(therapistTMS);  // Bind to TableView
    }

    @FXML
    void handleAssignPrograms(ActionEvent event) {

    }

    @FXML
    void handleClear(ActionEvent event) throws SQLException, ClassNotFoundException {
        refreshPage();
        statusLabel.setText("Form cleared");
    }

    @FXML
    void handleDelete(ActionEvent event) {
        String therapistId = therapistIdLabel.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this therapist?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();

        if (buttonType.isPresent() && buttonType.get() == ButtonType.YES) {
            try {
                boolean isDeleted = therapistBO.delete(therapistId);

                if (isDeleted) {
                    new Alert(Alert.AlertType.INFORMATION, "Therapist deleted successfully!").show();
                    refreshTable();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to delete therapist!").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "An error occurred during deletion!").show();
            }
        }
    }

    @FXML
    void handleEdit(ActionEvent event) {
        String id = therapistIdLabel.getText();
        String name = txtName.getText();
        String email = txtEmail.getText();
        String phone = txtPhone.getText();
        String specification = txtSpecification.getText();
        String status = statusComboBox.getValue().toString();

        String namePattern = "^[A-Za-z ]+$";
        String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        String phonePattern = "^(\\d{10})$";

        boolean isValidName = name.matches(namePattern);
        boolean isValidEmail = email.matches(emailPattern);
        boolean isValidPhone = phone.matches(phonePattern);

        txtName.setStyle("-fx-border-color: #7367F0;");
        txtEmail.setStyle("-fx-border-color: #7367F0;");
        txtPhone.setStyle("-fx-border-color: #7367F0;");
        txtSpecification.setStyle("-fx-border-color: #7367F0;");

        if (!isValidName) txtName.setStyle("-fx-border-color: red;");
        if (!isValidEmail) txtEmail.setStyle("-fx-border-color: red;");
        if (!isValidPhone) txtPhone.setStyle("-fx-border-color: red;");

        if (isValidName && isValidEmail && isValidPhone) {
            TherapistDTO therapistDTO = new TherapistDTO(id, name, email, phone, specification, status);

            try {
                boolean isUpdated = therapistBO.update(therapistDTO);

                if (isUpdated) {
                    new Alert(Alert.AlertType.INFORMATION, "Therapist updated successfully!").show();
                    refreshTable();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to update therapist!").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "An error occurred during update!").show();
            }
        }
    }

    @FXML
    void handleSave(ActionEvent event) {
        String id = therapistIdLabel.getText();
        String name = txtName.getText();
        String email = txtEmail.getText();
        String phone = txtPhone.getText();
        String specification = txtSpecification.getText();
        String status = statusComboBox.getValue().toString();

        // Define regex patterns
        String namePattern = "^[A-Za-z ]+$";
        String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        String phonePattern = "^(\\d{10})$"; // 10 digits

        // Validate fields
        boolean isValidName = name.matches(namePattern);
        boolean isValidEmail = email.matches(emailPattern);
        boolean isValidPhone = phone.matches(phonePattern);

        // Reset input styles (adjust if using TextField instead of Label)
        txtName.setStyle("-fx-border-color: #7367F0;");
        txtEmail.setStyle("-fx-border-color: #7367F0;");
        txtPhone.setStyle("-fx-border-color: #7367F0;");
        txtSpecification.setStyle("-fx-border-color: #7367F0;");

        // Highlight invalid fields
        if (!isValidName) txtName.setStyle("-fx-border-color: red;");
        if (!isValidEmail) txtEmail.setStyle("-fx-border-color: red;");
        if (!isValidPhone) txtPhone.setStyle("-fx-border-color: red;");

        if (isValidName && isValidEmail && isValidPhone) {
            TherapistDTO therapistDTO = new TherapistDTO(id, name, email, phone, specification, status);

            try {
                boolean isSaved = therapistBO.save(therapistDTO);

                if (isSaved) {
                    new Alert(Alert.AlertType.INFORMATION, "Therapist saved successfully!").show();
                    refreshTable();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to save therapist.").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "An error occurred.").show();
            }
        }
    }

    @FXML
    void handleSearch(ActionEvent event) {
        String id = searchField.getText();
        try {
            Optional<TherapistDTO> therapistOpt = therapistBO.findById(id);
            if (therapistOpt.isPresent()) {
                TherapistDTO therapist = therapistOpt.get();
                therapistIdLabel.setText(therapist.getTId());
                txtName.setText(therapist.getName());
                txtEmail.setText(therapist.getEmail());
                txtPhone.setText(therapist.getPhone());
                txtSpecification.setText(therapist.getSpecialization());
                statusComboBox.setValue(therapist.getStatus());
                statusLabel.setText("Therapist found.");
            } else {
                statusLabel.setText("Therapist not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            statusLabel.setText("Error searching therapist.");
        }
    }

    @FXML
    void handleViewSchedule(ActionEvent event) {

    }

    @FXML
    void onClickTable(MouseEvent event) {
        TherapistTM selectedItem = therapistTable.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            therapistIdLabel.setText(selectedItem.getTId());
            txtName.setText(selectedItem.getName());
            txtEmail.setText(selectedItem.getEmail());
            txtPhone.setText(selectedItem.getPhone());
            txtSpecification.setText(selectedItem.getSpecialization());
            statusComboBox.setValue(selectedItem.getStatus());

            saveButton.setDisable(true);
            editButton.setDisable(false);
            deleteButton.setDisable(false);
        }
    }

    @FXML
    void showAddTherapistDialog(ActionEvent event) {
        saveButton.setDisable(false);
    }

}
