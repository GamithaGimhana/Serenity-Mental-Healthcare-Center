package com.gdse.serenity.controller;

import com.gdse.serenity.bo.BOFactory;
import com.gdse.serenity.bo.custom.UserBO;
import com.gdse.serenity.dto.UserDTO;
import com.gdse.serenity.entity.User;
import com.gdse.serenity.util.EncryptionUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AccountSettingsController implements Initializable {

    @FXML
    private Label userIdLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label phoneLabel;

    @FXML
    private Label roleLabel;

    @FXML
    private Label currentUsernameLabel;

    @FXML
    private TextField newUsernameField;

    @FXML
    private PasswordField currentPasswordField;

    @FXML
    private PasswordField newPasswordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Button updateUsernameButton;

    @FXML
    private Button updatePasswordButton;

    @FXML
    private Button closeButton;

    private User currentUser;

    private final UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOType.USER);

    public void setCurrentUser(User user) {
        this.currentUser = user;
        populateUserInfo();
    }

    private void populateUserInfo() {
        if (currentUser != null) {
            userIdLabel.setText(currentUser.getUserId());
            nameLabel.setText(currentUser.getName());
            emailLabel.setText(currentUser.getEmail());
            phoneLabel.setText(currentUser.getPhone());
            roleLabel.setText(currentUser.getRole());
            currentUsernameLabel.setText(currentUser.getUsername());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // No additional setup needed now
    }

    @FXML
    private void handleUpdateUsername(ActionEvent event) {
        String newUsername = newUsernameField.getText().trim();

        if (newUsername.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Username cannot be empty");
            return;
        }

        if (newUsername.equals(currentUser.getUsername())) {
            showAlert(Alert.AlertType.WARNING, "Warning", "New username is the same as current username");
            return;
        }

        try {
            boolean usernameExists = isUsernameExists(newUsername);
            if (usernameExists) {
                showAlert(Alert.AlertType.ERROR, "Error", "Username already exists");
                return;
            }

            // Update username
            currentUser.setUsername(newUsername);
            UserDTO userDTO = new UserDTO(
                    currentUser.getUserId(),
                    currentUser.getName(),
                    currentUser.getEmail(),
                    currentUser.getPhone(),
                    currentUser.getUsername(),
                    currentUser.getPassword(),
                    currentUser.getRole()
            );

            boolean isUpdated = userBO.update(userDTO);

            if (isUpdated) {
                currentUsernameLabel.setText(newUsername);
                newUsernameField.clear();
                showAlert(Alert.AlertType.INFORMATION, "Success", "Username updated successfully");
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to update username");
            }

        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to update username: " + e.getMessage());
        }
    }

    @FXML
    private void handleUpdatePassword(ActionEvent event) {
        String currentPassword = currentPasswordField.getText();
        String newPassword = newPasswordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (currentPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "All password fields must be filled");
            return;
        }

        // 🛠️ Corrected password checking
        if (!EncryptionUtil.checkPassword(currentPassword, currentUser.getPassword())) {
            showAlert(Alert.AlertType.ERROR, "Error", "Current password is incorrect");
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            showAlert(Alert.AlertType.ERROR, "Error", "New password and confirmation do not match");
            return;
        }

        if (newPassword.equals(currentPassword)) {
            showAlert(Alert.AlertType.WARNING, "Warning", "New password is the same as current password");
            return;
        }

        try {
            // 🛠️ Save the **hashed new password**, not plain text
            currentUser.setPassword(EncryptionUtil.hashPassword(newPassword));

            UserDTO userDTO = new UserDTO(
                    currentUser.getUserId(),
                    currentUser.getName(),
                    currentUser.getEmail(),
                    currentUser.getPhone(),
                    currentUser.getUsername(),
                    currentUser.getPassword(),
                    currentUser.getRole()
            );

            boolean isUpdated = userBO.update(userDTO);

            if (isUpdated) {
                currentPasswordField.clear();
                newPasswordField.clear();
                confirmPasswordField.clear();
                showAlert(Alert.AlertType.INFORMATION, "Success", "Password updated successfully");
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to update password");
            }

        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to update password: " + e.getMessage());
        }
    }

    @FXML
    private void handleClose(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    private boolean isUsernameExists(String username) throws Exception {
        List<UserDTO> allUsers = userBO.getAll();
        for (UserDTO userDTO : allUsers) {
            if (userDTO.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
