package com.gdse.serenity.controller;

import com.gdse.serenity.entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;

public class ReceptionistDashboardController {

    @FXML
    private Button accountSettingsButton;

    @FXML
    private Label activePatientsLabel;

    @FXML
    private StackPane contentArea;

    @FXML
    private Button dashboardButton;

    @FXML
    private Button logoutButton;

    @FXML
    private TableColumn<?, ?> patientColumn;

    @FXML
    private Button patientManagementButton;

    @FXML
    private Button paymentManagementButton;

    @FXML
    private Label pendingPaymentsLabel;

    @FXML
    private TableColumn<?, ?> programColumn;

    @FXML
    private Button reportsButton;

    @FXML
    private Button sessionSchedulingButton;

    @FXML
    private TableColumn<?, ?> statusColumn;

    @FXML
    private TableColumn<?, ?> therapistColumn;

    @FXML
    private TableColumn<?, ?> timeColumn;

    @FXML
    private Label todayAppointmentsLabel;

    @FXML
    private TableView<?> todayScheduleTable;

    @FXML
    private Label userNameLabel;

    @FXML
    void handleLogout(ActionEvent event) {

    }

    @FXML
    void openAccountSettings(ActionEvent event) {

    }

    @FXML
    void showDashboard(ActionEvent event) {

    }

    @FXML
    void showPatientManagement(ActionEvent event) {

    }

    @FXML
    void showPaymentManagement(ActionEvent event) {

    }

    @FXML
    void showReports(ActionEvent event) {

    }

    @FXML
    void showSessionScheduling(ActionEvent event) {

    }

    public void initData(User user) {
    }
}
