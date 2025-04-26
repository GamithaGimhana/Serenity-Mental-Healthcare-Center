package com.gdse.serenity.controller;

import com.gdse.serenity.bo.BOFactory;
import com.gdse.serenity.bo.custom.impl.PatientBOImpl;
import com.gdse.serenity.bo.custom.impl.PaymentBOImpl;
import com.gdse.serenity.dto.PatientDTO;
import com.gdse.serenity.dto.PaymentDTO;
import com.gdse.serenity.view.tdm.PaymentTM;
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
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class PaymentManagementController implements Initializable {

    @FXML
    private Button addPaymentButton;

    @FXML
    private TableColumn<PaymentTM, String> amountColumn;

    @FXML
    private AnchorPane ancPayment;

    @FXML
    private Button clearButton;

    @FXML
    private TableColumn<PaymentTM, String> dateColumn;

    @FXML
    private Button deleteButton;

    @FXML
    private Button editButton;

    @FXML
    private ComboBox<String> filterPatientComboBox;

    @FXML
    private ComboBox<String> filterStatusComboBox;

    @FXML
    private Button generateInvoiceButton;

    @FXML
    private TableColumn<PaymentTM, String> idColumn;

    @FXML
    private TableColumn<PaymentTM, String> methodColumn;

    @FXML
    private TableColumn<PaymentTM, String> patientColumn;

    @FXML
    private ComboBox<String> patientComboBox;

    @FXML
    private Label patientEmailLabel;

    @FXML
    private Label patientNameLabel;

    @FXML
    private Label patientPhoneLabel;

    @FXML
    private DatePicker paymentDatePicker;

    @FXML
    private Label paymentIdLabel;

    @FXML
    private ComboBox<String> paymentMethodComboBox;

    @FXML
    private TableView<PaymentTM> paymentTable;

    @FXML
    private Button saveButton;

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchField;

    @FXML
    private TableColumn<PaymentTM, String> statusColumn;

    @FXML
    private ComboBox<String> statusComboBox;

    @FXML
    private Label statusLabel;

    @FXML
    private TextField txtAmount;

    PaymentBOImpl paymentBO = (PaymentBOImpl) BOFactory.getInstance().getBO(BOFactory.BOType.PAYMENT);
    PatientBOImpl patientBO = (PatientBOImpl) BOFactory.getInstance().getBO(BOFactory.BOType.PATIENT);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("payId"));
        patientColumn.setCellValueFactory(new PropertyValueFactory<>("patient_id"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("paymentDate"));
        methodColumn.setCellValueFactory(new PropertyValueFactory<>("paymentMethod"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        try {
            refreshPage();
        } catch (SQLException | ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void refreshPage() throws SQLException, ClassNotFoundException, IOException {
        refreshTable();

        String nextID = paymentBO.getNextId();
        paymentIdLabel.setText(nextID);
        patientComboBox.getSelectionModel().clearSelection();
        txtAmount.setText("");
        paymentDatePicker.setValue(null);
        paymentMethodComboBox.getSelectionModel().clearSelection();
        statusComboBox.getSelectionModel().clearSelection();

        loadMethods();
        loadStatus();
        loadPatientIds();

        searchField.clear();

        saveButton.setDisable(true);
        deleteButton.setDisable(true);
        editButton.setDisable(true);
    }

    private void loadPatientIds() throws SQLException, ClassNotFoundException, IOException {
        List<String> patientIds = patientBO.getAllIds();
        ObservableList<String> patientIdList = FXCollections.observableArrayList(patientIds);
        patientComboBox.setItems(patientIdList);
    }

    private void loadMethods() {
        paymentMethodComboBox.setItems(FXCollections.observableArrayList("Card", "Cash"));
    }

    private void loadStatus() {
        statusComboBox.setItems(FXCollections.observableArrayList("Completed", "Not Completed"));
    }

    private void refreshTable() throws SQLException, ClassNotFoundException, IOException {
        List<PaymentDTO> payments = paymentBO.getAll();  // Get all users from the BO
        ObservableList<PaymentTM> paymentTMs = FXCollections.observableArrayList();

        for (PaymentDTO payment : payments) {
            PaymentTM paymentTM = new PaymentTM(
                    payment.getPayId(),
                    payment.getAmount(),
                    payment.getPaymentDate(),
                    payment.getPaymentMethod(),
                    payment.getStatus(),
                    payment.getPatient()
            );
            paymentTMs.add(paymentTM);
        }

        paymentTable.setItems(paymentTMs);  // Bind to TableView
    }

    @FXML
    void handleClear(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        refreshPage();
        statusLabel.setText("Form cleared");
    }

    @FXML
    void handleDelete(ActionEvent event) {
        String paymentId = paymentIdLabel.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this payment?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();

        if (buttonType.isPresent() && buttonType.get() == ButtonType.YES) {
            try {
                boolean isDeleted = paymentBO.delete(paymentId);

                if (isDeleted) {
                    new Alert(Alert.AlertType.INFORMATION, "Payment deleted successfully!").show();
                    refreshTable();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to delete payment!").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "An error occurred during deletion!").show();
            }
        }
    }

    @FXML
    void handleEdit(ActionEvent event) {
        String id = paymentIdLabel.getText();
        String amount = txtAmount.getText();
        LocalDate date = paymentDatePicker.getValue();
        String method = paymentMethodComboBox.getValue();
        String status = statusComboBox.getValue();
        String patientId = patientComboBox.getValue();

        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setPId(patientId);
        patientDTO.setStatus(statusComboBox.getValue()); // Update status to 'Complete'

        PaymentDTO paymentDTO = new PaymentDTO(id, Double.parseDouble(amount), date, method, status, patientDTO);

        try {
            boolean isSaved = paymentBO.update(paymentDTO);

            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Payment updated successfully!").show();
                refreshTable();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update payment.").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "An error occurred.").show();
        }
    }

    @FXML
    void handleGenerateInvoice(ActionEvent event) {

    }

    @FXML
    void handleSave(ActionEvent event) {
        String id = paymentIdLabel.getText();
        String amount = txtAmount.getText();
        LocalDate date = paymentDatePicker.getValue();
        String method = paymentMethodComboBox.getValue();
        String status = statusComboBox.getValue();
        String patientId = patientComboBox.getValue();

        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setPId(patientId);
        patientDTO.setStatus(statusComboBox.getValue());

        PaymentDTO paymentDTO = new PaymentDTO(id, Double.parseDouble(amount), date, method, status, patientDTO);

        try {
            boolean isSaved = paymentBO.save(paymentDTO);

            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Payment saved successfully!").show();
                refreshTable();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save payment.").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "An error occurred.").show();
        }
    }

    @FXML
    void cmbPatientOnAction(ActionEvent event) {
        String id = patientComboBox.getValue();
        try {
            Optional<PatientDTO> patientDTO = patientBO.findById(id);
            if (patientDTO.isPresent()) {
                PatientDTO patient = patientDTO.get();
                patientNameLabel.setText(patient.getName());
                patientPhoneLabel.setText(patient.getPhone());
                patientEmailLabel.setText(patient.getEmail());
                statusLabel.setText("Patient found.");
            } else {
                statusLabel.setText("Patient not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            statusLabel.setText("Error searching Patient.");
        }
    }

    @FXML
    void handleSearch(ActionEvent event) {
        String id = searchField.getText();
        try {
            Optional<PaymentDTO> paymentDTO = paymentBO.findById(id);
            if (paymentDTO.isPresent()) {
                PaymentDTO payment = paymentDTO.get();
                paymentIdLabel.setText(payment.getPayId());
                patientComboBox.setValue(String.valueOf(payment.getPatient()));
                txtAmount.setText(String.valueOf(payment.getAmount()));
                paymentDatePicker.setValue(payment.getPaymentDate());
                paymentMethodComboBox.setValue(payment.getStatus());
                statusComboBox.setValue(payment.getStatus());
                statusLabel.setText("Payment found.");
            } else {
                statusLabel.setText("Payment not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            statusLabel.setText("Error searching payment.");
        }
    }

    @FXML
    void onClickTable(MouseEvent event) {
        PaymentTM selectedItem = paymentTable.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            paymentIdLabel.setText(selectedItem.getPayId());
            patientComboBox.setValue(selectedItem.getStatus());
            txtAmount.setText(String.valueOf(selectedItem.getAmount()));
            paymentDatePicker.setValue(selectedItem.getPaymentDate());
            paymentMethodComboBox.setValue(selectedItem.getStatus());
            statusComboBox.setValue(selectedItem.getStatus());

            saveButton.setDisable(true);
            editButton.setDisable(false);
            deleteButton.setDisable(false);
        }
    }

    @FXML
    void openAddPaymentDialog(ActionEvent event) {
        saveButton.setDisable(false);
    }
}
