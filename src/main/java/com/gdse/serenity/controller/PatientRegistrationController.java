package com.gdse.serenity.controller;

import com.gdse.serenity.bo.BOFactory;
import com.gdse.serenity.bo.custom.impl.RegistrationBOImpl;
import com.gdse.serenity.bo.custom.impl.TherapistBOImpl;
import com.gdse.serenity.bo.custom.impl.TherapyProgramBOImpl;
import com.gdse.serenity.dto.PatientDTO;
import com.gdse.serenity.dto.RegistrationDTO;
import com.gdse.serenity.dto.TherapyProgramDTO;
import com.gdse.serenity.dto.UserDTO;
import com.gdse.serenity.util.EncryptionUtil;
import com.gdse.serenity.view.tdm.RegistrationTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class PatientRegistrationController implements Initializable {

    @FXML
    private AnchorPane ancRegistration;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnRegister;

    @FXML
    private TableColumn<?, ?> colSpecialization;

    @FXML
    private TableColumn<?, ?> colTherapistId;

    @FXML
    private TableColumn<?, ?> colTherapistName;

    @FXML
    private Label lblCost;

    @FXML
    private Label lblDuration;

    @FXML
    private Label lblPatientId;

    @FXML
    private Label lblPatientName;

    @FXML
    private Label lblProgramName;

    @FXML
    private Label lblValidationMessage;

    @FXML
    private ComboBox<String> programComboBox;

    @FXML
    private Label statusLabel;

    @FXML
    private TableView<RegistrationTM> therapistTable;

    @FXML
    private TextField txtDownPayment;

    RegistrationBOImpl registrationBO = (RegistrationBOImpl) BOFactory.getInstance().getBO(BOFactory.BOType.REGISTRATION);
    TherapistBOImpl therapistBO = (TherapistBOImpl) BOFactory.getInstance().getBO(BOFactory.BOType.THERAPIST);
    TherapyProgramBOImpl therapyProgramBO = (TherapyProgramBOImpl) BOFactory.getInstance().getBO(BOFactory.BOType.THERAPY_PROGRAM);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colTherapistId.setCellValueFactory(new PropertyValueFactory<>("tId"));
        colTherapistName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colSpecialization.setCellValueFactory(new PropertyValueFactory<>("specialization"));

        try {
            refreshPage();
        } catch (SQLException | ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void refreshPage() throws SQLException, ClassNotFoundException, IOException {
        refreshTable();

        String nextID = therapistBO.getNextId();
        lblPatientId.setText(nextID);
        lblPatientName.setText("");
        programComboBox.getSelectionModel().clearSelection();
        lblProgramName.setText("");
        lblDuration.setText("");
        lblCost.setText("");
        txtDownPayment.setText("");

        loadProgram();
    }

    private void loadProgram() throws SQLException, IOException, ClassNotFoundException {
        List<String> programIds = therapyProgramBO.getAllIds();
        ObservableList<String> observableList = FXCollections.observableArrayList(programIds);
        programComboBox.setItems(observableList);

    }

    private void refreshTable() throws SQLException, ClassNotFoundException, IOException {
        List<RegistrationDTO> registrations = registrationBO.getAll();  // Get all from the BO
        ObservableList<RegistrationTM> registrationTMS = FXCollections.observableArrayList();

        for (RegistrationDTO registration : registrations) {
            RegistrationTM registrationTM = new RegistrationTM(
                    registration.getId(),
                    registration.getPatient(),
                    registration.getTherapyProgram(),
                    registration.getDownPayment()
            );
            registrationTMS.add(registrationTM);
        }

        therapistTable.setItems(registrationTMS);  // Bind to TableView
    }

    @FXML
    void cmbProgramOnAction(ActionEvent event) {
        String id = programComboBox.getValue();
        try {
            Optional<TherapyProgramDTO> therapyProgramDTO = therapyProgramBO.findById(id);
            if (therapyProgramDTO.isPresent()) {
                TherapyProgramDTO therapyProgram = therapyProgramDTO.get();
                lblProgramName.setText(therapyProgram.getName());
                lblDuration.setText(String.valueOf(therapyProgram.getDurationInWeeks()));
                lblCost.setText(String.valueOf(therapyProgram.getCost()));
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
    void handleCancel(ActionEvent event) {
        Stage stage = (Stage) lblPatientId.getScene().getWindow();
        stage.close();
    }

    @FXML
    void handleRegister(ActionEvent event) {
        String patientId = lblPatientId.getText();
        String programId = programComboBox.getValue();
        double downPayment = Double.parseDouble(txtDownPayment.getText());

        RegistrationDTO registrationDTO = new RegistrationDTO(patientId, programId, downPayment);

        try {
            boolean isSaved = registrationBO.save(registrationDTO);

            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Registration saved successfully!").show();
                refreshTable();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save Registration.").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "An error occurred.").show();
        }
    }


}
