package com.gdse.serenity.controller;

import com.gdse.serenity.bo.BOFactory;
import com.gdse.serenity.bo.custom.impl.*;
import com.gdse.serenity.dto.PatientDTO;
import com.gdse.serenity.dto.PaymentDTO;
import com.gdse.serenity.dto.TherapySessionDTO;
import com.gdse.serenity.dto.UserDTO;
import com.gdse.serenity.view.tdm.TherapyProgramTM;
import com.gdse.serenity.view.tdm.TherapySessionTM;
import com.gdse.serenity.view.tdm.UserTM;
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

public class TherapySessionController implements Initializable {

    @FXML
    private Button addSessionButton;

    @FXML
    private AnchorPane ancTherapySession;

    @FXML
    private Button applyFilterButton;

    @FXML
    private DatePicker availabilityDatePicker;

    @FXML
    private Label availabilityStatusLabel;

    @FXML
    private Button checkAvailabilityButton;

    @FXML
    private Button clearButton;

    @FXML
    private TableColumn<TherapySessionTM, String> dateColumn;

    @FXML
    private Button deleteButton;

    @FXML
    private Button editButton;

    @FXML
    private ComboBox<String> filterComboBox;

    @FXML
    private DatePicker filterDatePicker;

    @FXML
    private TableColumn<TherapySessionTM, String> idColumn;

    @FXML
    private TableColumn<TherapySessionTM, String> patientColumn;

    @FXML
    private ComboBox<String> patientComboBox;

    @FXML
    private TableColumn<TherapySessionTM, String> programColumn;

    @FXML
    private ComboBox<String> programComboBox;

    @FXML
    private Button saveButton;

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchField;

    @FXML
    private DatePicker sessionDatePicker;

    @FXML
    private Label sessionIdLabel;

    @FXML
    private TableView<TherapySessionTM> sessionTable;

    @FXML
    private Label statusLabel;

    @FXML
    private TableColumn<TherapySessionTM, String> therapistColumn;

    @FXML
    private ComboBox<String> therapistComboBox;

    TherapySessionBOImpl therapySessionBO = (TherapySessionBOImpl) BOFactory.getInstance().getBO(BOFactory.BOType.THERAPY_SESSION);
    PatientBOImpl patientBO = (PatientBOImpl) BOFactory.getInstance().getBO(BOFactory.BOType.PATIENT);
    TherapistBOImpl therapistBO = (TherapistBOImpl) BOFactory.getInstance().getBO(BOFactory.BOType.THERAPIST);
    TherapyProgramBOImpl therapyProgramBO = (TherapyProgramBOImpl) BOFactory.getInstance().getBO(BOFactory.BOType.THERAPY_PROGRAM);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("tsId"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("sessionDate"));
        therapistColumn.setCellValueFactory(new PropertyValueFactory<>("therapist"));
        patientColumn.setCellValueFactory(new PropertyValueFactory<>("patient"));
        programColumn.setCellValueFactory(new PropertyValueFactory<>("program"));

        try {
            refreshPage();
        } catch (SQLException | ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void refreshPage() throws SQLException, ClassNotFoundException, IOException {
        refreshTable();

        String nextID = therapySessionBO.getNextId();
        sessionIdLabel.setText(nextID);
        sessionDatePicker.setValue(null);
        therapistComboBox.getSelectionModel().clearSelection();
        patientComboBox.getSelectionModel().clearSelection();
        programComboBox.getSelectionModel().clearSelection();

        loadPatientIds();
        loadTherapistIds();
        loadProgramIds();

        searchField.clear();

        saveButton.setDisable(true);
        deleteButton.setDisable(true);
        editButton.setDisable(true);

    }

    private void loadPatientIds() throws SQLException, ClassNotFoundException, IOException {
        List<String> patientIds = patientBO.getAllIds();
        ObservableList<String> patientIdList = FXCollections.observableArrayList(patientIds);
        patientComboBox.setItems(patientIdList);
    }

    private void loadTherapistIds() throws SQLException, ClassNotFoundException, IOException {
        List<String> therapistIds = therapistBO.getAllIds();
        ObservableList<String> therapistIdList = FXCollections.observableArrayList(therapistIds);
        therapistComboBox.setItems(therapistIdList);
    }

    private void loadProgramIds() throws SQLException, ClassNotFoundException, IOException {
        List<String> therapyProgramIds = therapyProgramBO.getAllIds();
        ObservableList<String> therapyProgramIdList = FXCollections.observableArrayList(therapyProgramIds);
        programComboBox.setItems(therapyProgramIdList);
    }

    private void refreshTable() throws SQLException, ClassNotFoundException, IOException {
        List<TherapySessionDTO> therapySessions = therapySessionBO.getAll();  // Get all users from the BO
        ObservableList<TherapySessionTM> therapySessionTMS = FXCollections.observableArrayList();

        for (TherapySessionDTO therapySession : therapySessions) {
            TherapySessionTM therapySessionTM = new TherapySessionTM(
                    therapySession.getTsId(),
                    therapySession.getSessionDate(),
                    therapySession.getTherapist(),
                    therapySession.getPatient(),
                    therapySession.getProgram()
            );
            therapySessionTMS.add(therapySessionTM);
        }

        sessionTable.setItems(therapySessionTMS);  // Bind to TableView
    }

    @FXML
    void handleApplyFilter(ActionEvent event) {

    }

    @FXML
    void handleCheckAvailability(ActionEvent event) {

    }

    @FXML
    void handleClear(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        refreshPage();
        statusLabel.setText("Form cleared");
    }

    @FXML
    void handleDelete(ActionEvent event) {
        String sessionId = sessionIdLabel.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this Therapy Session?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();

        if (buttonType.isPresent() && buttonType.get() == ButtonType.YES) {
            try {
                boolean isDeleted = therapySessionBO.delete(sessionId);

                if (isDeleted) {
                    new Alert(Alert.AlertType.INFORMATION, "Therapy Session deleted successfully!").show();
                    refreshTable();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to delete Therapy Session!").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "An error occurred during deletion!").show();
            }
        }
    }

    @FXML
    void handleEdit(ActionEvent event) {
        String id = sessionIdLabel.getText();
        LocalDate date = sessionDatePicker.getValue();
        String therapistId = therapistComboBox.getValue();
        String patientId = patientComboBox.getValue();
        String programId = programComboBox.getValue();

        TherapySessionDTO therapySessionDTO = new TherapySessionDTO(id, date, therapistId, patientId, programId);

        try {
            boolean isSaved = therapySessionBO.update(therapySessionDTO);

            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Therapy Session saved successfully!").show();
                refreshTable();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save Therapy Session.").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "An error occurred.").show();
        }
    }

    @FXML
    void handleSave(ActionEvent event) {
        String id = sessionIdLabel.getText();
        LocalDate date = sessionDatePicker.getValue();
        String therapistId = therapistComboBox.getValue();
        String patientId = patientComboBox.getValue();
        String programId = programComboBox.getValue();

        TherapySessionDTO therapySessionDTO = new TherapySessionDTO(id, date, therapistId, patientId, programId);

        try {
            boolean isSaved = therapySessionBO.save(therapySessionDTO);

            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Therapy Session saved successfully!").show();
                refreshTable();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save Therapy Session.").show();
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
            Optional<TherapySessionDTO> therapySessionDTO = therapySessionBO.findById(id);
            if (therapySessionDTO.isPresent()) {
                TherapySessionDTO therapySession = therapySessionDTO.get();
                sessionIdLabel.setText(therapySession.getTsId());
                sessionDatePicker.setValue(therapySession.getSessionDate());
                therapistComboBox.setValue(String.valueOf(therapySession.getTherapist()));
                patientComboBox.setValue(String.valueOf(therapySession.getPatient()));
                programComboBox.setValue(String.valueOf(therapySession.getProgram()));
                statusLabel.setText("Therapy Session found.");
            } else {
                statusLabel.setText("Therapy Session not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            statusLabel.setText("Error searching Therapy Session.");
        }
    }

    @FXML
    void onClickTable(MouseEvent event) {
        TherapySessionTM selectedItem = sessionTable.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            sessionIdLabel.setText(selectedItem.getTsId());
            sessionDatePicker.setValue(selectedItem.getSessionDate());
            therapistComboBox.setValue(String.valueOf(selectedItem.getTherapist()));
            patientComboBox.setValue(String.valueOf(selectedItem.getPatient()));
            programComboBox.setValue(String.valueOf(selectedItem.getProgram()));

            saveButton.setDisable(true);
            editButton.setDisable(false);
            deleteButton.setDisable(false);
        }
    }

    @FXML
    void showAddSessionDialog(ActionEvent event) {
        saveButton.setDisable(false);
    }

}
