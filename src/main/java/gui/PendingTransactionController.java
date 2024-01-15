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
    @FXML private TableColumn<PendingTransaction,String> transactionTypeColumn;
    @FXML private TableColumn<PendingTransaction,String> transactionAmountColumn;
    @FXML private TableColumn<PendingTransaction,String> targetAccountColumn;
    @FXML private TableColumn<PendingTransaction,String> transactionInitiatorColumn;
    @FXML private Button authoriseAllButton;
    @FXML private Button closeButton;
    public PendingTransactionController(){
    }

    @FXML private void initialize(){
        // set TableColumns
        transactionTypeColumn.setCellValueFactory(new PropertyValueFactory<>("transactionType"));
        transactionAmountColumn.setCellValueFactory(new PropertyValueFactory<>("transactionAmount"));
        targetAccountColumn.setCellValueFactory(new PropertyValueFactory<>("targetAccountNumber"));
        transactionInitiatorColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));

        for (PendingTransaction transaction : pendingTransactionList){
            if (transaction.getTargetAccountNumber()!=null) targetAccountColumn.setVisible(true);
            pendingTransactionTable.getItems().add(transaction);

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
    @FXML private void pendingTransactionSelection() {
        PendingTransaction selectedTransaction = pendingTransactionTable.getSelectionModel().getSelectedItem();
        if (selectedTransaction != null && confirmAuthorisationDialog(selectedTransaction)==0) {
            pendingTransactionApplication.authoriseTransaction(selectedTransaction);
        }

    }
    @FXML private void authoriseAllButtonAction(){
        int x = confirmAuthoriseAllDialog();
        if (x==0) {
            for (PendingTransaction transaction : pendingTransactionList) {
                pendingTransactionApplication.authoriseTransaction(transaction);
            }
        }
    }

    @FXML private void closeButtonAction(){
        pendingTransactionApplication.close();
    }

    int confirmAuthorisationDialog(PendingTransaction pendingTransaction){
        Alert confirmAuthorisation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAuthorisation.setTitle("Confirm Authorisation");
        confirmAuthorisation.setHeaderText("Authorise £" + pendingTransaction.getTransactionAmount() + " " + pendingTransaction.getTransactionType());
        Optional<ButtonType> result = confirmAuthorisation.showAndWait();
        if (result.isPresent() && result.get()==ButtonType.OK){
            return 0;
        } else return -1;
    }
    int confirmAuthoriseAllDialog(){
        Alert confirmAuthoriseAll = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAuthoriseAll.setTitle("Confirm Authorisation");
        confirmAuthoriseAll.setHeaderText("Authorise all of the transactions listed here?");
        Optional<ButtonType> result = confirmAuthoriseAll.showAndWait();
        if (result.isPresent() && result.get()==ButtonType.OK){
            return 0;
        } else return -1;
    }

    void updateTable(PendingTransaction pendingTransaction){
        pendingTransactionTable.getItems().remove(pendingTransaction);
        pendingTransactionTable.refresh();
    }
}
