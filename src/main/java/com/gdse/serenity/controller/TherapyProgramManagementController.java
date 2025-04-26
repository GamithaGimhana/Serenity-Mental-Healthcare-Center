package com.gdse.serenity.controller;

import com.gdse.serenity.bo.BOFactory;
import com.gdse.serenity.bo.custom.impl.PatientBOImpl;
import com.gdse.serenity.bo.custom.impl.TherapistBOImpl;
import com.gdse.serenity.bo.custom.impl.TherapyProgramBOImpl;
import com.gdse.serenity.dto.PatientDTO;
import com.gdse.serenity.dto.TherapistDTO;
import com.gdse.serenity.dto.TherapyProgramDTO;
import com.gdse.serenity.view.tdm.TherapyProgramTM;
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
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class TherapyProgramManagementController implements Initializable {

    @FXML
    private Button addProgramButton;

    @FXML
    private AnchorPane ancTherapyProgram;

    @FXML
    private Button assignTherapistsButton;

    @FXML
    private ListView<String> assignedTherapistsListView;

    @FXML
    private Button clearButton;

    @FXML
    private TableColumn<TherapyProgramTM, String> costColumn;

    @FXML
    private Button deleteButton;

    @FXML
    private TableColumn<TherapyProgramTM, String> durationColumn;

    @FXML
    private Button editButton;

    @FXML
    private ListView<String> enrolledPatientsListView;

    @FXML
    private ComboBox<String> filterComboBox;

    @FXML
    private TableColumn<TherapyProgramTM, String> idColumn;

    @FXML
    private TableColumn<TherapyProgramTM, String> nameColumn;

    @FXML
    private Label programIdLabel;

    @FXML
    private TableView<TherapyProgramTM> programTable;

    @FXML
    private Button saveButton;

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchField;

    @FXML
    private Label statusLabel;

    @FXML
    private TextField txtCost;

    @FXML
    private TextField txtDuration;

    @FXML
    private TextField txtName;

    TherapyProgramBOImpl therapyProgramBO = (TherapyProgramBOImpl) BOFactory.getInstance().getBO(BOFactory.BOType.THERAPY_PROGRAM);
    PatientBOImpl patientBO = (PatientBOImpl) BOFactory.getInstance().getBO(BOFactory.BOType.PATIENT);
    TherapistBOImpl therapistBO = (TherapistBOImpl) BOFactory.getInstance().getBO(BOFactory.BOType.THERAPIST);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("programId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        costColumn.setCellValueFactory(new PropertyValueFactory<>("cost"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("durationInWeeks"));

        try {
            refreshPage();
        } catch (SQLException | ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void refreshPage() throws SQLException, ClassNotFoundException, IOException {
        refreshTable();

        String nextID = therapyProgramBO.getNextId();
        programIdLabel.setText(nextID);
        txtName.setText("");
        txtDuration.setText("");
        txtCost.setText("");
        assignedTherapistsListView.getItems().clear();
        enrolledPatientsListView.getItems().clear();

        searchField.clear();

        saveButton.setDisable(true);
        deleteButton.setDisable(true);
        editButton.setDisable(true);

    }

    private void refreshTable() throws SQLException, ClassNotFoundException, IOException {
        List<TherapyProgramDTO> therapyPrograms = therapyProgramBO.getAll();  // Get all from the BO
        ObservableList<TherapyProgramTM> therapyProgramTMS = FXCollections.observableArrayList();

        for (TherapyProgramDTO therapyProgram : therapyPrograms) {
            TherapyProgramTM therapyProgramTM = new TherapyProgramTM(
                    therapyProgram.getProgramId(),
                    therapyProgram.getName(),
                    therapyProgram.getDurationInWeeks(),
                    therapyProgram.getCost()
            );
            therapyProgramTMS.add(therapyProgramTM);
        }

        programTable.setItems(therapyProgramTMS);
    }

    @FXML
    void handleAssignTherapists(ActionEvent event) {

    }

    @FXML
    void handleClear(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        refreshPage();
        statusLabel.setText("Form cleared");
    }

    @FXML
    void handleDelete(ActionEvent event) {
        String programId = programIdLabel.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this Therapy Program?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();

        if (buttonType.isPresent() && buttonType.get() == ButtonType.YES) {
            try {
                boolean isDeleted = therapyProgramBO.delete(programId);

                if (isDeleted) {
                    new Alert(Alert.AlertType.INFORMATION, "Therapy Program deleted successfully!").show();
                    refreshTable();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to delete Therapy Program!").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "An error occurred during deletion!").show();
            }
        }
    }

    @FXML
    void handleEdit(ActionEvent event) {
        String id = programIdLabel.getText();
        String name = txtName.getText();
        String cost = txtCost.getText();
        int duration = Integer.parseInt(txtDuration.getText());

        TherapyProgramDTO therapyProgramDTO = new TherapyProgramDTO(id, name, duration, Double.parseDouble(cost));

        try {
            boolean isSaved = therapyProgramBO.update(therapyProgramDTO);

            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Therapy Program saved successfully!").show();
                refreshTable();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save Therapy Program.").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "An error occurred.").show();
        }
    }

    @FXML
    void handleSave(ActionEvent event) {
        String id = programIdLabel.getText();
        String name = txtName.getText();
        String cost = txtCost.getText();
        int duration = Integer.parseInt(txtDuration.getText());

        TherapyProgramDTO therapyProgramDTO = new TherapyProgramDTO(id, name, duration, Double.parseDouble(cost));

        try {
            boolean isSaved = therapyProgramBO.save(therapyProgramDTO);

            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Therapy Program saved successfully!").show();
                refreshTable();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save Therapy Program.").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "An error occurred.").show();
        }
    }

    @FXML
    void handleSearch(ActionEvent event) {
        String id = searchField.getText();
        try {
            Optional<TherapyProgramDTO> therapyProgramOpt = therapyProgramBO.findById(id);
            if (therapyProgramOpt.isPresent()) {
                TherapyProgramDTO therapyProgram = therapyProgramOpt.get();
                programIdLabel.setText(therapyProgram.getProgramId());
                txtName.setText(therapyProgram.getName());
                txtDuration.setText(String.valueOf(therapyProgram.getDurationInWeeks()));
                txtCost.setText(String.valueOf(therapyProgram.getCost()));

                loadAssignedTherapists(therapyProgram.getProgramId());
                loadEnrolledPatients(therapyProgram.getProgramId());

                statusLabel.setText("Therapy Program found.");
            } else {
                statusLabel.setText("Therapy Program not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            statusLabel.setText("Error searching Therapy Program.");
        }
    }

    @FXML
    void onClickTable(MouseEvent event) throws SQLException, ClassNotFoundException, IOException {
        TherapyProgramTM selectedItem = programTable.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            programIdLabel.setText(selectedItem.getProgramId());
            txtName.setText(selectedItem.getName());
            txtDuration.setText(String.valueOf(selectedItem.getDurationInWeeks()));
            txtCost.setText(String.valueOf(selectedItem.getCost()));

            loadAssignedTherapists(selectedItem.getProgramId());
            loadEnrolledPatients(selectedItem.getProgramId());

            saveButton.setDisable(true);
            editButton.setDisable(false);
            deleteButton.setDisable(false);
        }
    }

    @FXML
    void showAddProgramDialog(ActionEvent event) {
        saveButton.setDisable(false);
    }

    private void loadEnrolledPatients(String programId) throws SQLException, ClassNotFoundException, IOException {
        List<String> patients = patientBO.getEnrolledPatients(programId);  // Get patient names from BO
        ObservableList<String> patientList = FXCollections.observableArrayList(patients);
        enrolledPatientsListView.setItems(patientList);
    }

    private void loadAssignedTherapists(String programId) throws SQLException, ClassNotFoundException, IOException {
        List<String> therapists = therapistBO.getAssignedTherapists(programId);  // Get therapist names from BO
        ObservableList<String> therapistList = FXCollections.observableArrayList(therapists);
        assignedTherapistsListView.setItems(therapistList);
    }

}
