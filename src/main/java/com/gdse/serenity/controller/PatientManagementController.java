package com.gdse.serenity.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class PatientManagementController {

    @FXML
    private Button addPatientButton;

    @FXML
    private Button clearButton;

    @FXML
    private TableColumn<?, ?> contactColumn;

    @FXML
    private Button deleteButton;

    @FXML
    private Button editButton;

    @FXML
    private TableColumn<?, ?> emailColumn;

    @FXML
    private ListView<?> enrolledProgramsListView;

    @FXML
    private ComboBox<?> filterComboBox;

    @FXML
    private TableColumn<?, ?> idColumn;

    @FXML
    private TableColumn<?, ?> nameColumn;

    @FXML
    private TableColumn<?, ?> newRegDateColumn;

    @FXML
    private TableColumn<?, ?> newRegIdColumn;

    @FXML
    private TableColumn<?, ?> newRegNameColumn;

    @FXML
    private TableColumn<?, ?> newRegProgramColumn;

    @FXML
    private TableView<?> newRegistrationsTable;

    @FXML
    private Label patientEmailLabel;

    @FXML
    private Label patientIdLabel;

    @FXML
    private Label patientJoinDateLabel;

    @FXML
    private Label patientNameLabel;

    @FXML
    private Label patientPhoneLabel;

    @FXML
    private TableView<?> patientTable;

    @FXML
    private TableColumn<?, ?> programsColumn;

    @FXML
    private Button saveButton;

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchField;

    @FXML
    private TableColumn<?, ?> statusColumn;

    @FXML
    private TableColumn<?, ?> statusColumn1;

    @FXML
    private ComboBox<?> statusComboBox;

    @FXML
    private Label statusLabel;

    @FXML
    private Button viewScheduleButton;

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
    void handleViewSchedule(ActionEvent event) {

    }

    @FXML
    void openAddPatientDialog(ActionEvent event) {

    }

}
