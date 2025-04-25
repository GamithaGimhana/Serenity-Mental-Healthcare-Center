package com.gdse.serenity.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class PaymentManagementController {

    @FXML
    private Button addPaymentButton;

    @FXML
    private TableColumn<?, ?> amountColumn;

    @FXML
    private AnchorPane ancPayment;

    @FXML
    private Button clearButton;

    @FXML
    private TableColumn<?, ?> dateColumn;

    @FXML
    private Button deleteButton;

    @FXML
    private Button editButton;

    @FXML
    private ComboBox<?> filterPatientComboBox;

    @FXML
    private ComboBox<?> filterStatusComboBox;

    @FXML
    private Button generateInvoiceButton;

    @FXML
    private TableColumn<?, ?> idColumn;

    @FXML
    private TableColumn<?, ?> methodColumn;

    @FXML
    private TableColumn<?, ?> patientColumn;

    @FXML
    private ComboBox<?> patientComboBox;

    @FXML
    private Label patientEmailLabel;

    @FXML
    private Label patientNameLabel;

    @FXML
    private Label patientPhoneLabel;

    @FXML
    private DatePicker paymentDatePicker;

    @FXML
    private Label paymentIdLabel;

    @FXML
    private ComboBox<?> paymentMethodComboBox;

    @FXML
    private TableView<?> paymentTable;

    @FXML
    private Button saveButton;

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchField;

    @FXML
    private TableColumn<?, ?> statusColumn;

    @FXML
    private ComboBox<?> statusComboBox;

    @FXML
    private Label statusLabel;

    @FXML
    private TextField txtAmount;

    @FXML
    void handleClear(ActionEvent event) {

    }

    @FXML
    void handleDelete(ActionEvent event) {

    }

    @FXML
    void handleEdit(ActionEvent event) {

    }

    @FXML
    void handleGenerateInvoice(ActionEvent event) {

    }

    @FXML
    void handleSave(ActionEvent event) {

    }

    @FXML
    void handleSearch(ActionEvent event) {

    }

    @FXML
    void onClickTable(MouseEvent event) {

    }

    @FXML
    void openAddPaymentDialog(ActionEvent event) {

    }

}
