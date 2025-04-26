package com.gdse.serenity.controller;

import com.gdse.serenity.bo.BOFactory;
import com.gdse.serenity.bo.custom.impl.PatientBOImpl;
import com.gdse.serenity.dto.PatientDTO;
import com.gdse.serenity.view.tdm.PatientTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class PatientManagementController implements Initializable {

    @FXML
    private Button addPatientButton;

    @FXML
    private AnchorPane ancPatient;

    @FXML
    private Button assignProgramsButton;

    @FXML
    private Button clearButton;

    @FXML
    private TableColumn<PatientTM, String> phoneColumn;

    @FXML
    private Button deleteButton;

    @FXML
    private Button editButton;

    @FXML
    private TableColumn<PatientTM, String> emailColumn;

    @FXML
    private ListView<String> enrolledProgramsListView;

    @FXML
    private ComboBox<String> filterComboBox;

    @FXML
    private TableColumn<PatientTM, String> idColumn;

    @FXML
    private TableColumn<PatientTM, String> nameColumn;

    @FXML
    private Label patientIdLabel;

    @FXML
    private TableView<PatientTM> patientTable;

    @FXML
    private TableColumn<PatientTM, String> dateColumn;

    @FXML
    private TableColumn<PatientTM, String> mediHisColumn;

    @FXML
    private Button saveButton;

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchField;

    @FXML
    private TableColumn<PatientTM, String> statusColumn;

    @FXML
    private ComboBox<String> statusComboBox;

    @FXML
    private Label statusLabel;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtMediHistory;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPhone;

    @FXML
    private DatePicker txtRegDate;

    @FXML
    private Button viewScheduleButton;

    PatientBOImpl patientBO = (PatientBOImpl) BOFactory.getInstance().getBO(BOFactory.BOType.PATIENT);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("pId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("registrationDate"));
        mediHisColumn.setCellValueFactory(new PropertyValueFactory<>("medicalHistory"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        try {
            refreshPage();
        } catch (SQLException | ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void refreshPage() throws SQLException, ClassNotFoundException, IOException {
        refreshTable();

        String nextID = patientBO.getNextId();
        patientIdLabel.setText(nextID);
        txtName.setText("");
        txtEmail.setText("");
        txtPhone.setText("");
        txtMediHistory.setText("");
        txtRegDate.setValue(null);
        statusComboBox.getSelectionModel().clearSelection();
        enrolledProgramsListView.getItems().clear();

        loadStatus();

        searchField.clear();

        saveButton.setDisable(true);
        deleteButton.setDisable(true);
        editButton.setDisable(true);

    }

    private void loadStatus() {
        statusComboBox.setItems(FXCollections.observableArrayList("Completed", "Not Completed"));
    }

    private void refreshTable() throws SQLException, ClassNotFoundException, IOException {
        List<PatientDTO> patients = patientBO.getAll();  // Get all from the BO
        ObservableList<PatientTM> patientTMS = FXCollections.observableArrayList();

        for (PatientDTO patient : patients) {
            PatientTM patientTM = new PatientTM(
                    patient.getPId(),
                    patient.getName(),
                    patient.getEmail(),
                    patient.getPhone(),
                    patient.getRegistrationDate(),
                    patient.getMedicalHistory(),
                    patient.getStatus()
            );
            patientTMS.add(patientTM);
        }

        patientTable.setItems(patientTMS);
    }

    @FXML
    void handleAssignPrograms(ActionEvent event) {

    }

    @FXML
    void handleClear(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        refreshPage();
        statusLabel.setText("Form cleared");
    }

    @FXML
    void handleDelete(ActionEvent event) {
        String patientId = patientIdLabel.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this patient?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();

        if (buttonType.isPresent() && buttonType.get() == ButtonType.YES) {
            try {
                boolean isDeleted = patientBO.delete(patientId);

                if (isDeleted) {
                    new Alert(Alert.AlertType.INFORMATION, "Patient deleted successfully!").show();
                    refreshTable();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to delete patient!").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "An error occurred during deletion!").show();
            }
        }
    }

    @FXML
    void handleEdit(ActionEvent event) {
        String id = patientIdLabel.getText();
        String name = txtName.getText();
        String email = txtEmail.getText();
        String phone = txtPhone.getText();
        String mediHistory = txtMediHistory.getText();
        LocalDate regDate = txtRegDate.getValue();
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
        txtMediHistory.setStyle("-fx-border-color: #7367F0;");
        txtRegDate.setStyle("-fx-border-color: #7367F0;");

        if (!isValidName) txtName.setStyle("-fx-border-color: red;");
        if (!isValidEmail) txtEmail.setStyle("-fx-border-color: red;");
        if (!isValidPhone) txtPhone.setStyle("-fx-border-color: red;");

        if (isValidName && isValidEmail && isValidPhone) {
            PatientDTO patientDTO = new PatientDTO(id, name, email, phone, mediHistory, regDate, status);

            try {
                boolean isUpdated = patientBO.update(patientDTO);

                if (isUpdated) {
                    new Alert(Alert.AlertType.INFORMATION, "Patient updated successfully!").show();
                    refreshTable();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to update patient!").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "An error occurred during update!").show();
            }
        }
    }

    @FXML
    void handleSave(ActionEvent event) {
        String id = patientIdLabel.getText();
        String name = txtName.getText();
        String email = txtEmail.getText();
        String phone = txtPhone.getText();
        String mediHistory = txtMediHistory.getText();
        LocalDate regDate = txtRegDate.getValue();
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
        txtMediHistory.setStyle("-fx-border-color: #7367F0;");
        txtRegDate.setStyle("-fx-border-color: #7367F0;");

        // Highlight invalid fields
        if (!isValidName) txtName.setStyle("-fx-border-color: red;");
        if (!isValidEmail) txtEmail.setStyle("-fx-border-color: red;");
        if (!isValidPhone) txtPhone.setStyle("-fx-border-color: red;");

        if (isValidName && isValidEmail && isValidPhone) {
            PatientDTO patientDTO = new PatientDTO(id, name, email, phone, mediHistory, regDate, status);

            try {
                boolean isSaved = patientBO.save(patientDTO);

                if (isSaved) {
                    new Alert(Alert.AlertType.INFORMATION, "Patient saved successfully!").show();
                    refreshTable();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to save patient.").show();
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
            Optional<PatientDTO> patientOpt = patientBO.findById(id);
            if (patientOpt.isPresent()) {
                PatientDTO patient = patientOpt.get();
                patientIdLabel.setText(patient.getPId());
                txtName.setText(patient.getName());
                txtEmail.setText(patient.getEmail());
                txtPhone.setText(patient.getPhone());
                txtMediHistory.setText(patient.getMedicalHistory());
                txtRegDate.setValue(patient.getRegistrationDate());
                statusComboBox.setValue(patient.getStatus());
                loadEnrolledPrograms(patient.getPId());
                statusLabel.setText("Patient found.");
            } else {
                statusLabel.setText("Patient not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            statusLabel.setText("Error searching patient.");
        }
    }

    @FXML
    void handleViewSchedule(ActionEvent event) {

    }

    @FXML
    void openAddPatientDialog(ActionEvent event) {
        saveButton.setDisable(false);
    }

    @FXML
    void onClickTable(MouseEvent event) throws SQLException, ClassNotFoundException, IOException {
        PatientTM selectedItem = patientTable.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            patientIdLabel.setText(selectedItem.getPId());

            txtName.setText(selectedItem.getName());
            txtEmail.setText(selectedItem.getEmail());
            txtPhone.setText(selectedItem.getPhone());
            txtMediHistory.setText(selectedItem.getMedicalHistory());
            txtRegDate.setValue(selectedItem.getRegistrationDate());
            statusComboBox.setValue(selectedItem.getStatus());
            loadEnrolledPrograms(selectedItem.getPId());

            saveButton.setDisable(true);
            editButton.setDisable(false);
            deleteButton.setDisable(false);
        }
    }

    private void loadEnrolledPrograms(String patientId) throws SQLException, ClassNotFoundException, IOException {
        List<String> programs = patientBO.getEnrolledPrograms(patientId);  // get from BO
        ObservableList<String> programList = FXCollections.observableArrayList(programs);
        enrolledProgramsListView.setItems(programList);
    }
}
