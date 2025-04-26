package com.gdse.serenity.controller;

import com.gdse.serenity.config.FactoryConfiguration;
import com.gdse.serenity.entity.User;
import com.gdse.serenity.util.EncryptionUtil;
import com.gdse.serenity.util.ValidationUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.IOException;

public class LoginController {

    @FXML
    private AnchorPane ancLogin;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private HBox passwordContainer;

    @FXML
    private Button togglePasswordButton;

    @FXML
    private Button loginButton;

    @FXML
    private Label errorMessageLabel;

    private TextField visiblePasswordField;
    private boolean passwordVisible = false;

    @FXML
    public void initialize() {
        // Create a visible text field that will replace the password field when toggled
        visiblePasswordField = new TextField();
        visiblePasswordField.setPromptText("Enter password");
        visiblePasswordField.setPrefWidth(passwordField.getPrefWidth());

        // Ensure the error message is cleared when user starts typing
        usernameField.textProperty().addListener((obs, oldVal, newVal) -> {
            errorMessageLabel.setText("");
        });

        passwordField.textProperty().addListener((obs, oldVal, newVal) -> {
            errorMessageLabel.setText("");
        });
    }

    @FXML
    private void togglePasswordVisibility(ActionEvent event) {
        if (!passwordVisible) {
            // Show plain text password
            visiblePasswordField.setText(passwordField.getText());
            visiblePasswordField.setFont(passwordField.getFont());

            int index = passwordContainer.getChildren().indexOf(passwordField);
            if (index != -1) {
                passwordContainer.getChildren().set(index, visiblePasswordField);
            }

            togglePasswordButton.setText("🔒"); // Change button text/icon
            passwordVisible = true;
        } else {
            // Hide password
            passwordField.setText(visiblePasswordField.getText());

            int index = passwordContainer.getChildren().indexOf(visiblePasswordField);
            if (index != -1) {
                passwordContainer.getChildren().set(index, passwordField);
            }

            togglePasswordButton.setText("👁"); // Change button text/icon
            passwordVisible = false;
        }
    }

    @FXML
    private void handleLogin(ActionEvent event) {
        String username = usernameField.getText().trim();
        String password = passwordVisible ? visiblePasswordField.getText() : passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            errorMessageLabel.setText("Username and password cannot be empty");
            return;
        }

        try {
            if (!ValidationUtil.isValidUsername(username)) {
                errorMessageLabel.setText("Invalid username format");
                return;
            }

            try (Session session = FactoryConfiguration.getInstance().getSession()) {
                session.beginTransaction();

                // Check if default admin exists
                Query<User> checkAdminQuery = session.createQuery("FROM User WHERE username = :username", User.class);
                checkAdminQuery.setParameter("username", "admin");
                User existingAdmin = checkAdminQuery.uniqueResult();

                // If not found, create the admin user
                if (existingAdmin == null) {
                    User defaultAdmin = new User();
                    defaultAdmin.setUserId("U000");
                    defaultAdmin.setName("System Administrator");
                    defaultAdmin.setEmail("admin@serenity.com");
                    defaultAdmin.setPhone("0000000000");
                    defaultAdmin.setUsername("admin");
                    defaultAdmin.setPassword(EncryptionUtil.hashPassword("1234"));
                    defaultAdmin.setRole("ADMIN");
                    session.save(defaultAdmin);
                }

                session.getTransaction().commit();
            }

            // Reopen session to handle login
            try (Session session = FactoryConfiguration.getInstance().getSession()) {
                session.beginTransaction();

                Query<User> query = session.createQuery("FROM User WHERE username = :username", User.class);
                query.setParameter("username", username);
                User user = query.uniqueResult();
                session.getTransaction().commit();

                if (user == null) {
                    errorMessageLabel.setText("Authentication failed");
                    return;
                }

                if (!EncryptionUtil.checkPassword(password, user.getPassword())) {
                    errorMessageLabel.setText("Invalid credentials");
                    return;
                }

                if ("ADMIN".equals(user.getRole())) {
                    loadAdminDashboard(user);
                } else if ("RECEPTIONIST".equals(user.getRole())) {
                    loadReceptionistDashboard(user);
                } else {
                    errorMessageLabel.setText("Unknown user role");
                }
            }

        } catch (Exception e) {
            errorMessageLabel.setText("An unexpected error occurred");
            e.printStackTrace();
        }
    }

    private void loadAdminDashboard(User user) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/adminDashboardFx.fxml"));
        Parent root = loader.load();

        AdminDashboardController controller = loader.getController();
        controller.initData(user);

        Stage stage = (Stage) loginButton.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Admin Dashboard - Serenity Mental Health Therapy Center");
        stage.show();
        stage.centerOnScreen();
    }

    private void loadReceptionistDashboard(User user) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/receptionistDashboardFx.fxml"));
        Parent root = loader.load();

        ReceptionistDashboardController controller = loader.getController();
        controller.initData(user);

        Stage stage = (Stage) loginButton.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Receptionist Dashboard - Serenity Mental Health Therapy Center");
        stage.show();
        stage.centerOnScreen();
    }
}