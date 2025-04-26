package com.gdse.serenity.controller;

import com.gdse.serenity.db.DBConnection;
import com.gdse.serenity.entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class AdminDashboardController {

    @FXML
    private Button accountSettingsButton;

    @FXML
    private Label activeProgramsLabel;

    @FXML
    private TableColumn<?, ?> activityColumn;

    @FXML
    private AnchorPane ancContent;

    @FXML
    private AnchorPane ancMenuPage;

    @FXML
    private StackPane contentArea;

    @FXML
    private Button dashboardButton;

    @FXML
    private TableColumn<?, ?> dateTimeColumn;

    @FXML
    private Button logoutButton;

    @FXML
    private TableView<?> recentActivityTable;

    @FXML
    private Button reportsButton;

    @FXML
    private Button therapistManagementButton;

    @FXML
    private Button therapyProgramButton;

    @FXML
    private Label totalTherapistsLabel;

    @FXML
    private Label totalUsersLabel;

    @FXML
    private TableColumn<?, ?> userColumn;

    @FXML
    private Button userManagementButton;

    @FXML
    private Label userNameLabel;

    private User currentUser;

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

                // Load the login page
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/loginFx.fxml"));
                Parent root = loader.load();

                Stage loginStage = new Stage();
                loginStage.setTitle("Login");
                loginStage.setScene(new Scene(root));
                loginStage.show();

                // Close the current Admin Dashboard window
                Stage currentStage = (Stage) logoutButton.getScene().getWindow();
                currentStage.close();

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
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/accountSettingFx.fxml"));
            Parent root = loader.load();

            AccountSettingsController controller = loader.getController();
            controller.setCurrentUser(currentUser);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Account Settings");
            stage.setScene(new Scene(root));
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load Account Settings page!").show();
        }
    }

    @FXML
    void showDashboard(ActionEvent event) {
        try {
            ancMenuPage.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource("/view/adminDashboardFx.fxml"));

//          Bind the loaded FXML to all edges of the content anchorPane
            load.prefWidthProperty().bind(ancMenuPage.widthProperty());
            load.prefHeightProperty().bind(ancMenuPage.heightProperty());

            ancMenuPage.getChildren().add(load);
        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load page!").show();
        }
    }

    @FXML
    void showReports(ActionEvent event) {

    }

    @FXML
    void showTherapistManagement(ActionEvent event) {
        navigateTo("/view/therapistManagementFx.fxml");
    }

    @FXML
    void showTherapyPrograms(ActionEvent event) {
        navigateTo("/view/therapyProgramManagementFx.fxml");
    }

    @FXML
    void showUserManagement(ActionEvent event) {
        navigateTo("/view/userManagementFx.fxml");
    }

    public void initData(User user) {
        currentUser = user;
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
