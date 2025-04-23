package com.gdse.serenity.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class AddPatientDialogController {

    @FXML
    private TextArea addressArea;

    @FXML
    private AnchorPane ancAddPatient;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField contactNumberField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField fullNameField;

    @FXML
    private ListView<?> programListView;

    @FXML
    private DatePicker registrationDatePicker;

    @FXML
    private Button saveButton;

    @FXML
    private Label totalFeeLabel;

}
