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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class TherapyProgramManagementController {

    @FXML
    private Button addProgramButton;

    @FXML
    private AnchorPane ancTherapyProgram;

    @FXML
    private Button assignTherapistsButton;

    @FXML
    private ListView<?> assignedTherapistsListView;

    @FXML
    private Button clearButton;

    @FXML
    private TableColumn<?, ?> costColumn;

    @FXML
    private Button deleteButton;

    @FXML
    private TableColumn<?, ?> durationColumn;

    @FXML
    private Button editButton;

    @FXML
    private ListView<?> enrolledPatientsListView;

    @FXML
    private ComboBox<?> filterComboBox;

    @FXML
    private TableColumn<?, ?> idColumn;

    @FXML
    private TableColumn<?, ?> nameColumn;

    @FXML
    private TableColumn<?, ?> patientsColumn;

    @FXML
    private Label programIdLabel;

    @FXML
    private TableView<?> programTable;

    @FXML
    private Button saveButton;

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchField;

    @FXML
    private Label statusLabel;

    @FXML
    private TableColumn<?, ?> therapistsColumn;

    @FXML
    private TextField txtCost;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtDuration;

    @FXML
    private TextField txtName;

    @FXML
    void handleAssignTherapists(ActionEvent event) {

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
    void showAddProgramDialog(ActionEvent event) {

    }

}
