package com.gdse.serenity.controller;

import com.gdse.serenity.db.DBConnection;
import com.gdse.serenity.entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class ReceptionistDashboardController {

    @FXML
    private Button accountSettingsButton;

    @FXML
    private Label activePatientsLabel;

    @FXML
    private AnchorPane ancReception;

    @FXML
    private AnchorPane ancContent;

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
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {
            try {
                // Close the database connection if it's still open
                DBConnection dbConnection = DBConnection.getInstance();
                if (dbConnection != null && dbConnection.getConnection() != null && !dbConnection.getConnection().isClosed()) {
                    dbConnection.getConnection().close();
                    System.out.println("Database connection closed on logout.");
                }

                // Clear the current menu page and load the login page
                ancReception.getChildren().clear();
                AnchorPane load = FXMLLoader.load(getClass().getResource("/view/loginFx.fxml"));
                load.prefWidthProperty().bind(ancReception.widthProperty());
                load.prefHeightProperty().bind(ancReception.heightProperty());

                ancReception.getChildren().add(load);

            } catch (IOException e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Failed to load the login page!").show();
            } catch (SQLException e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Database error during logout!").show();
            }
        }
    }

    @FXML
    void openAccountSettings(ActionEvent event) {

    }

    @FXML
    void showDashboard(ActionEvent event) {
        try {
            ancReception.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource("/view/receptionistDashboardFx.fxml"));

//          Bind the loaded FXML to all edges of the content anchorPane
            load.prefWidthProperty().bind(ancReception.widthProperty());
            load.prefHeightProperty().bind(ancReception.heightProperty());

            ancReception.getChildren().add(load);
        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load page!").show();
        }
    }

    @FXML
    void showPatientManagement(ActionEvent event) {
        navigateTo("/view/patientManagementFx.fxml");
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

    public void navigateTo(String fxmlPath) {
        try {
            ancContent.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource(fxmlPath));

//          Bind the loaded FXML to all edges of the content anchorPane
            load.prefWidthProperty().bind(ancContent.widthProperty());
            load.prefHeightProperty().bind(ancContent.heightProperty());

            ancContent.getChildren().add(load);
        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load page!").show();
        }
    }
}
