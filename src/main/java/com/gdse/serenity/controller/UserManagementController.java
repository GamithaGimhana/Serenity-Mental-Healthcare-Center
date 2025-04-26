package com.gdse.serenity.controller;

import com.gdse.serenity.bo.BOFactory;
import com.gdse.serenity.bo.custom.impl.UserBOImpl;
import com.gdse.serenity.dto.UserDTO;
import com.gdse.serenity.util.EncryptionUtil;
import com.gdse.serenity.view.tdm.UserTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class UserManagementController implements Initializable {

    @FXML
    private Button addUserButton;

    @FXML
    private AnchorPane ancUser;

    @FXML
    private Button clearButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button editButton;

    @FXML
    private TableColumn<UserTM, String> emailColumn;

    @FXML
    private ComboBox<String> filterComboBox;

    @FXML
    private TableColumn<UserTM, String> idColumn;

    @FXML
    private TableColumn<UserTM, String> nameColumn;

    @FXML
    private TableColumn<UserTM, String> phoneColumn;

    @FXML
    private TableColumn<UserTM, String> roleColumn;

    @FXML
    private ComboBox<String> roleComboBox;

    @FXML
    private Button saveButton;

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchField;

    @FXML
    private Label statusLabel;

    @FXML
    private Label userIdLabel;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtUsername;

    @FXML
    private TableView<UserTM> userTable;

    @FXML
    private TableColumn<UserTM, String> usernameColumn;

    UserBOImpl userBO = (UserBOImpl) BOFactory.getInstance().getBO(BOFactory.BOType.USER);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));

        try {
            refreshPage();
        } catch (SQLException | ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void refreshPage() throws SQLException, ClassNotFoundException, IOException {
        refreshTable();

        String nextUserID = userBO.getNextId();
        userIdLabel.setText(nextUserID);
        txtName.setText("");
        txtEmail.setText("");
        txtPassword.setText("");
        txtPhone.setText("");
        txtUsername.setText("");
        roleComboBox.getSelectionModel().clearSelection();

        loadRoles();

        searchField.clear();

        saveButton.setDisable(true);
        deleteButton.setDisable(true);
        editButton.setDisable(true);

    }

    private void loadRoles() {
        roleComboBox.setItems(FXCollections.observableArrayList("ADMIN", "RECEPTIONIST"));
    }

    private void refreshTable() throws SQLException, ClassNotFoundException, IOException {
        List<UserDTO> users = userBO.getAll();  // Get all users from the BO
        ObservableList<UserTM> userTMs = FXCollections.observableArrayList();

        for (UserDTO user : users) {
            UserTM userTM = new UserTM(
                    user.getUserId(),
                    user.getName(),
                    user.getEmail(),
                    user.getPhone(),
                    user.getUsername(),
                    user.getRole()
            );
            userTMs.add(userTM);
        }

        userTable.setItems(userTMs);  // Bind to TableView
    }

    @FXML
    void handleClear(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        refreshPage();
        statusLabel.setText("Form cleared");
    }

    @FXML
    void handleDelete(ActionEvent event) {
        String userId = userIdLabel.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this user?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();

        if (buttonType.isPresent() && buttonType.get() == ButtonType.YES) {
            try {
                boolean isDeleted = userBO.delete(userId);

                if (isDeleted) {
                    new Alert(Alert.AlertType.INFORMATION, "User deleted successfully!").show();
                    refreshTable();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to delete user!").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "An error occurred during deletion!").show();
            }
        }
    }

    @FXML
    void handleEdit(ActionEvent event) {
        String id = userIdLabel.getText();
        String name = txtName.getText();
        String email = txtEmail.getText();
        String phone = txtPhone.getText();
        String username = txtUsername.getText();
        String password = EncryptionUtil.hashPassword(txtPassword.getText());
        String role = roleComboBox.getValue().toString();

        String namePattern = "^[A-Za-z ]+$";
        String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        String phonePattern = "^(\\d{10})$";

        boolean isValidName = name.matches(namePattern);
        boolean isValidEmail = email.matches(emailPattern);
        boolean isValidPhone = phone.matches(phonePattern);

        txtName.setStyle("-fx-border-color: #7367F0;");
        txtEmail.setStyle("-fx-border-color: #7367F0;");
        txtPhone.setStyle("-fx-border-color: #7367F0;");
        txtUsername.setStyle("-fx-border-color: #7367F0;");
        txtPassword.setStyle("-fx-border-color: #7367F0;");

        if (!isValidName) txtName.setStyle("-fx-border-color: red;");
        if (!isValidEmail) txtEmail.setStyle("-fx-border-color: red;");
        if (!isValidPhone) txtPhone.setStyle("-fx-border-color: red;");

        if (isValidName && isValidEmail && isValidPhone) {
            UserDTO userDTO = new UserDTO(id, name, email, phone, username, password, role);

            try {
                boolean isUpdated = userBO.update(userDTO);

                if (isUpdated) {
                    new Alert(Alert.AlertType.INFORMATION, "User updated successfully!").show();
                    refreshTable();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to update user!").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "An error occurred during update!").show();
            }
        }
    }

    @FXML
    void handleSave(ActionEvent event) {
        String id = userIdLabel.getText();
        String name = txtName.getText();
        String email = txtEmail.getText();
        String phone = txtPhone.getText();
        String username = txtUsername.getText();
        String password = EncryptionUtil.hashPassword(txtPassword.getText());
        String role = roleComboBox.getValue().toString();

        // Define regex patterns
        String namePattern = "^[A-Za-z ]+$";
        String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        String phonePattern = "^(\\d{10})$"; // 10 digits

        // Validate fields
        boolean isValidName = name.matches(namePattern);
        boolean isValidEmail = email.matches(emailPattern);
        boolean isValidPhone = phone.matches(phonePattern);

        // Reset input styles (adjust if using TextField instead of Label)
        txtName.setStyle("-fx-border-color: #7367F0;");
        txtEmail.setStyle("-fx-border-color: #7367F0;");
        txtPhone.setStyle("-fx-border-color: #7367F0;");
        txtUsername.setStyle("-fx-border-color: #7367F0;");
        txtPassword.setStyle("-fx-border-color: #7367F0;");

        // Highlight invalid fields
        if (!isValidName) txtName.setStyle("-fx-border-color: red;");
        if (!isValidEmail) txtEmail.setStyle("-fx-border-color: red;");
        if (!isValidPhone) txtPhone.setStyle("-fx-border-color: red;");

        if (isValidName && isValidEmail && isValidPhone) {
            UserDTO userDTO = new UserDTO(id, name, email, phone, username, password, role);

            try {
                boolean isSaved = userBO.save(userDTO);

                if (isSaved) {
                    new Alert(Alert.AlertType.INFORMATION, "User saved successfully!").show();
                    refreshTable();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to save user.").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "An error occurred.").show();
            }
        }
    }

    @FXML
    void handleSearch(ActionEvent event) {
        String id = searchField.getText();
        try {
            Optional<UserDTO> userOpt = userBO.findById(id);
            if (userOpt.isPresent()) {
                UserDTO user = userOpt.get();
                userIdLabel.setText(user.getUserId());
                txtName.setText(user.getName());
                txtEmail.setText(user.getEmail());
                txtPhone.setText(user.getPhone());
                txtUsername.setText(user.getUsername());
                roleComboBox.setValue(user.getRole());
                statusLabel.setText("User found.");
            } else {
                statusLabel.setText("User not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            statusLabel.setText("Error searching user.");
        }
    }

    @FXML
    void showAddUserDialog(ActionEvent event) {
        saveButton.setDisable(false);
    }

    @FXML
    void onClickTable(MouseEvent event) {
        UserTM selectedItem = userTable.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            userIdLabel.setText(selectedItem.getUserId());
            txtName.setText(selectedItem.getName());
            txtEmail.setText(selectedItem.getEmail());
            txtPhone.setText(selectedItem.getPhone());
            txtUsername.setText(selectedItem.getUsername());
            roleComboBox.setValue(selectedItem.getRole());

            saveButton.setDisable(true);
            editButton.setDisable(false);
            deleteButton.setDisable(false);
        }
    }

}
