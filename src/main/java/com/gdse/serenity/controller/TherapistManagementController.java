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

public class TherapistManagementController {

    @FXML
    private Button addTherapistButton;

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
    private TableColumn<?, ?> emailColumn;

    @FXML
    private ComboBox<?> filterComboBox;

    @FXML
    private TableColumn<?, ?> idColumn;

    @FXML
    private TableColumn<?, ?> nameColumn;

    @FXML
    private TableColumn<?, ?> phoneColumn;

    @FXML
    private Button saveButton;

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchField;

    @FXML
    private TableColumn<?, ?> specializationColumn;

    @FXML
    private TableColumn<?, ?> statusColumn;

    @FXML
    private ComboBox<?> statusComboBox;

    @FXML
    private Label statusLabel;

    @FXML
    private Label therapistEmailLabel;

    @FXML
    private Label therapistIdLabel;

    @FXML
    private Label therapistJoinDateLabel;

    @FXML
    private Label therapistNameLabel;

    @FXML
    private Label therapistPhoneLabel;

    @FXML
    private Label therapistSpecializationLabel;

    @FXML
    private TableView<?> therapistTable;

    @FXML
    private Button viewScheduleButton;

    @FXML
    void handleAssignPrograms(ActionEvent event) {

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
    void handleViewSchedule(ActionEvent event) {

    }

    @FXML
    void showAddTherapistDialog(ActionEvent event) {

    }

}
