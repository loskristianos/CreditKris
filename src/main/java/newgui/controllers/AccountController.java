package newgui.controllers;

import newgui.applications.AccountApplication;
import newobjects.*;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.List;


public class AccountController {

    Account account;
    List<Account> accountList;
    List<Transaction> transactionList;
    AccountApplication accountApplication;

    // fxml fields
    @FXML private TextField accountNumberDisplay;
    @FXML private TextField accountTypeDisplay;
    @FXML private TextField currentBalanceDisplay;
    @FXML private TextField overdraftLimitDisplay;
    @FXML private Button transferButton;
    @FXML private ComboBox<Account> transferAccounts;
    @FXML private TableView<Transaction> transactionTableView;
    @FXML private TableColumn<Transaction,String> transactionTimeColumn;
    @FXML private TableColumn<Transaction,String> transactionTypeColumn;
    @FXML private TableColumn<Transaction,String> transactionAmountColumn;
    @FXML private TableColumn<Transaction,String> previousBalanceColumn;
    @FXML private TableColumn<Transaction,String> newBalanceColumn;
    @FXML private TableColumn<Transaction,String> transactionIdColumn;

    public AccountController(){}

    @FXML private void initialize(){
        // set text of display fields
        accountNumberDisplay.setText(account.getAccountNumber());
        accountTypeDisplay.setText(account.getAccountType().toString());
        currentBalanceDisplay.setText(account.getCurrentBalance().toString());
        overdraftLimitDisplay.setText(account.getOverdraftLimit().toString());
        //only display transfer button if user has more than one account
        if(accountList.size()>1) transferButton.setVisible(true);

        // set up ComboBox to list available accounts for transfer
        transferAccounts.setCellFactory(new Callback<ListView<Account>, ListCell<Account>>() {
            @Override
            public ListCell<Account> call(ListView<Account> accountListView) {
                return new ListCell<Account>() {
                    public void updateItem(Account item, boolean empty){
                        super.updateItem(item, empty);
                        if (item == null || empty) {setGraphic(null);}
                        else {setText(item.getAccountNumber() + ": " + item.getAccountType().toString() + " account");
                        }
                    }
                };
            }});

        for (Account account : accountList) {
            if (!account.getAccountNumber().equals(this.account.getAccountNumber()))
                transferAccounts.getItems().add(account);
        }

        //set up tableview for transactions
        transactionTimeColumn.setCellValueFactory(new PropertyValueFactory<>("transactionTime"));
        transactionTypeColumn.setCellValueFactory(new PropertyValueFactory<>("transactionType"));
        transactionAmountColumn.setCellValueFactory(new PropertyValueFactory<>("transactionAmount"));
        previousBalanceColumn.setCellValueFactory(new PropertyValueFactory<>("previousBalance"));
        newBalanceColumn.setCellValueFactory(new PropertyValueFactory<>("newBalance"));
        transactionIdColumn.setCellValueFactory(new PropertyValueFactory<>("transactionID"));

        for (Transaction transaction : transactionList) {
            transactionTableView.getItems().add(transaction);
        }
    }

    public void setAccountApplication(AccountApplication accountApplication) {
        this.accountApplication = accountApplication;
    }

    public void setTransactionList(List<Transaction> transactionList){
        this.transactionList = transactionList;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    @FXML private void backButtonAction(){
        accountApplication.goBack();
     }

    @FXML private void depositButtonAction(){
        String transactionAmount = confirmTransactionAmount("Deposit");
        displayDialog(accountApplication.createDeposit(transactionAmount),"Deposit");
    }

    @FXML private void withdrawalButtonAction(){
        String transactionAmount = confirmTransactionAmount("Withdrawal");
        displayDialog(accountApplication.createWithdrawal(transactionAmount),"Withdrawal");
    }

    @FXML private void transferButtonAction(){transferAccounts.setVisible(true);} // show account selection

    @FXML private void transferAccountAction(){
        Account selectedTransferAccount = transferAccounts.getSelectionModel().getSelectedItem();
        accountApplication.setTargetAccount(selectedTransferAccount);
        String transactionAmount = confirmTransactionAmount("Transfer");
        displayDialog(accountApplication.createTransfer(transactionAmount),"Transfer");
    }


    String confirmTransactionAmount(String transactionType){
        TextInputDialog transactionDialog = new TextInputDialog();
        transactionDialog.setTitle("New " +transactionType);
        transactionDialog.setHeaderText("Please enter the amount of your " +transactionType.toLowerCase()+".");
        transactionDialog.showAndWait();
        String transactionAmount = transactionDialog.getEditor().getText();
        if (transactionAmount == null || transactionAmount.isBlank()){
            transactionDialog.setContentText("You must enter an amount for this " + transactionType.toLowerCase()+".");
            transactionDialog.showAndWait();
        }
        return transactionDialog.getEditor().getText();
    }

    void displayDialog(int i, String transactionType){
        switch (i) {
            case 0:
                successDialog(transactionType);
                break;
            case -1:
                unspecifiedFailureDialog(transactionType);
                break;
            case -2:
                pendingTransactionsDialog(transactionType);
                break;
            case -3:
                overdraftLimitDialog(transactionType);
                break;
        }

    }

    // Transaction Success/Fail Dialogs
    void successDialog(String transactionType){
        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
        successAlert.setContentText(transactionType + " completed successfully.");
        successAlert.setHeaderText("Transaction Complete");
        successAlert.showAndWait();
    }

    void unspecifiedFailureDialog(String transactionType){
        Alert failureDialog = new Alert(Alert.AlertType.ERROR);
        failureDialog.setHeaderText("There was a problem processing your " +transactionType.toLowerCase() + ".");
        failureDialog.setContentText("We apologise for the inconvenience. Please contact our helpdesk for assistance.");
        failureDialog.showAndWait();
    }

    void overdraftLimitDialog(String transactionType){
        Alert overdraftAlert = new Alert(Alert.AlertType.WARNING);
        overdraftAlert.setHeaderText("Unable to proceed with this " + transactionType.toLowerCase()+".");
        overdraftAlert.setContentText("You do not have enough funds to complete this transaction.");
        overdraftAlert.showAndWait();
    }

    void pendingTransactionsDialog(String transactionType){
        Alert pendingAlert = new Alert(Alert.AlertType.INFORMATION);
        pendingAlert.setHeaderText("This " + transactionType.toLowerCase() + " requires authorisation by the other signatories to this account.");
        pendingAlert.setContentText("Once all signatories have authorised the transaction it will be promptly processed and your account balance updated accordingly.");
        pendingAlert.showAndWait();
    }





}
