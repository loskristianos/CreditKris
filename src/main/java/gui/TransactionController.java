package gui;

import account.Account;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import transaction.Transaction;

public class TransactionController {
    Stage currentStage;
    Account account;
    Transaction transaction;
    String customerName;
    TransactionApplication transactionApplication;

    @FXML private TextField transactionIdDisplay;
    @FXML private TextField transactionTypeDisplay;
    @FXML private TextField transactionAmountDisplay;
    @FXML private TextField transactionTimeDisplay;
    @FXML private TextField previousBalanceDisplay;
    @FXML private TextField newBalanceDisplay;
    @FXML private Label transactionInitiatorLabel;
    @FXML private TextField transactionInitiatorDisplay;
    @FXML private Label targetAccountLabel;
    @FXML private TextField targetAccountDisplay;

    public TransactionController() {}

    @FXML private void initialize(){
        transactionIdDisplay.setText(transaction.getTransactionID());
        transactionTypeDisplay.setText(transaction.getTransactionType());
        transactionAmountDisplay.setText(transaction.getTransactionAmount());
        transactionTimeDisplay.setText(transaction.getTransactionTime());
        previousBalanceDisplay.setText(transaction.getPreviousBalance());
        newBalanceDisplay.setText(transaction.getNewBalance());
        transactionInitiatorDisplay.setText(customerName);
        targetAccountDisplay.setText(transaction.getTargetAccountNumber());

        if(transaction.getTransactionType().equals("Transfer In")) {
            targetAccountLabel.setText("Transferred from account number");
            targetAccountLabel.setVisible(true);
            targetAccountDisplay.setVisible(true);
        }

        if (transaction.getTransactionType().equals("Transfer Out")){
            targetAccountLabel.setText("Transferred from account number");
            targetAccountLabel.setVisible(true);
            targetAccountDisplay.setVisible(true);
        }

        if (!account.getSignatories().equals("0")){
            transactionInitiatorLabel.setVisible(true);
            transactionInitiatorDisplay.setVisible(true);
        }
    }

    public void setCurrentStage(Stage currentStage) {
        this.currentStage = currentStage;
    }

    public void setTransactionApplication(TransactionApplication transactionApplication) {
        this.transactionApplication = transactionApplication;
    }
    public void setCustomerName(String name){
        customerName = name;
    }
    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @FXML void closeButtonAction(){
        currentStage.close();
    }


}
