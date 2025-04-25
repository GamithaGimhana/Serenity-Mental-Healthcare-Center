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

public class TherapySessionController {

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
    private TableColumn<?, ?> dateColumn;

    @FXML
    private Button deleteButton;

    @FXML
    private Button editButton;

    @FXML
    private ComboBox<?> filterComboBox;

    @FXML
    private DatePicker filterDatePicker;

    @FXML
    private TableColumn<?, ?> idColumn;

    @FXML
    private TableColumn<?, ?> patientColumn;

    @FXML
    private ComboBox<?> patientComboBox;

    @FXML
    private TableColumn<?, ?> programColumn;

    @FXML
    private ComboBox<?> programComboBox;

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
    private TableView<?> sessionTable;

    @FXML
    private Label statusLabel;

    @FXML
    private TableColumn<?, ?> therapistColumn;

    @FXML
    private ComboBox<?> therapistComboBox;

    @FXML
    void handleApplyFilter(ActionEvent event) {

    }

    @FXML
    void handleCheckAvailability(ActionEvent event) {

    }

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
    void handleSave(ActionEvent event) {

    }

    @FXML
    void handleSearch(ActionEvent event) {

    }

    @FXML
    void onClickTable(MouseEvent event) {

    }

    @FXML
    void showAddSessionDialog(ActionEvent event) {

    }

}
