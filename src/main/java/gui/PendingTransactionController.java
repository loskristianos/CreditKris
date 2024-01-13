package gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import transaction.PendingTransaction;

import java.util.List;
import java.util.Optional;

public class PendingTransactionController {
    PendingTransactionApplication pendingTransactionApplication;
    List<PendingTransaction> pendingTransactionList;
    Stage currentStage;

    @FXML private TableView<PendingTransaction> pendingTransactionTable;
    @FXML private TableColumn<PendingTransaction,String> transactionDateColumn;
    @FXML private TableColumn<PendingTransaction,String> transactionTypeColumn;
    @FXML private TableColumn<PendingTransaction,String> transactionAmountColumn;
    @FXML private TableColumn<PendingTransaction,String> targetAccountColumn;
    @FXML private TableColumn<PendingTransaction,String> transactionInitiatorColumn;
    @FXML private Button authoriseAllButton;
    @FXML private Button closeButton;
    public PendingTransactionController(){
    }

    @FXML private void initialise(){
        // set TableColumns
        transactionDateColumn.setCellValueFactory(new PropertyValueFactory<>("transactionTime"));
        transactionTypeColumn.setCellValueFactory(new PropertyValueFactory<>("transactionType"));
        transactionAmountColumn.setCellValueFactory(new PropertyValueFactory<>("transactionAmount"));
        targetAccountColumn.setCellValueFactory(new PropertyValueFactory<>("targetAccountNumber"));
        transactionInitiatorColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));

        for (PendingTransaction transaction : pendingTransactionList){
            pendingTransactionTable.getItems().add(transaction);
            if (transaction.getTargetAccountNumber()!=null) targetAccountColumn.setVisible(true);
        }
    }

    void setPendingTransactionApplication(PendingTransactionApplication pendingTransactionApplication){
        this.pendingTransactionApplication = pendingTransactionApplication;
    }

    void setPendingTransactionList(List<PendingTransaction> pendingTransactionList){
        this.pendingTransactionList = pendingTransactionList;
    }

    void setCurrentStage(Stage stage){
        currentStage = stage;
    }
    @FXML private void pendingTransactionSelection() throws Exception{
        PendingTransaction selectedTransaction = pendingTransactionTable.getSelectionModel().getSelectedItem();
        if (selectedTransaction != null) pendingTransactionApplication.selectPendingTransaction(selectedTransaction);

    }
    @FXML private void authoriseAllButtonAction(){
        for (PendingTransaction transaction : pendingTransactionList) {
            transaction.authorise();
        }
    }

    @FXML private void closeButtonAction(){
        currentStage.close();
    }

    int confirmAuthorisationDialog(PendingTransaction pendingTransaction){
        Alert confirmAuthorisation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAuthorisation.setTitle("Confirm Authorisation");
        confirmAuthorisation.setHeaderText("Authorise £" + pendingTransaction.getTransactionAmount() + " " + pendingTransaction.getTransactionType());
        Optional<ButtonType> result = confirmAuthorisation.showAndWait();
        if (result.get()==ButtonType.OK){
            return 0;
        } else return -1;
    }
}