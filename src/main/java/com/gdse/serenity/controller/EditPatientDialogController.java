package com.gdse.serenity.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class EditPatientDialogController {

    @FXML
    private TextArea addressArea;

    @FXML
    private AnchorPane ancEditPatient;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField contactNumberField;

    @FXML
    private Label currentProgramsLabel;

    @FXML
    private TextField emailField;

    @FXML
    private TextField fullNameField;

    @FXML
    private Label patientIdLabel;

    @FXML
    private ListView<?> programListView;

    @FXML
    private Button updateButton;

}
