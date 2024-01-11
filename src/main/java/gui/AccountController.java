package gui;

import account.Account;
import customer.Customer;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import transaction.Transaction;

import java.util.List;

public class AccountController {
    Stage currentStage;
    Scene previousScene;
    Account account;
    Customer customer;
    List<Transaction> transactionList;
    List<Account> accountList;
    AccountApplication accountApplication;

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
        accountNumberDisplay.setText(account.getAccountNumber());
        accountTypeDisplay.setText(account.getAccountType());
        currentBalanceDisplay.setText(account.getCurrentBalance());
        overdraftLimitDisplay.setText(account.getOverdraftLimit());
        if(accountList.size()>1) transferButton.setVisible(true);
        for (Account account : accountList){
            if (!account.getAccountNumber().equals(this.account.getAccountNumber()))
                transferAccounts.getItems().add(account);
        }

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

    public void setCurrentStage(Stage stage){
        currentStage = stage;
    }
    public void setPreviousScene(Scene scene) {
        previousScene = scene;
    }
    public void setAccount(Account account){
        this.account = account;
    }
    public void setCustomer(Customer customer){
        this.customer = customer;
    }
    public void setTransactionList(List<Transaction> transactionList){
        this.transactionList = transactionList;
    }
    public void setAccountList(List<Account> accountList){
        this.accountList = accountList;
    }

    public void setAccountApplication(AccountApplication accountApplication){
        this.accountApplication = accountApplication;
    }

    @FXML private void backButtonAction(){
        currentStage.setScene(previousScene);
    }

    @FXML private void depositButtonAction(){
        String depositAmount = confirmTransactionAmount();
        int x = accountApplication.createTransaction(depositAmount, "Deposit");
        if (x==0) successDialog("Deposit");
    }

    @FXML private void withdrawalButtonAction(){
        String withdrawalAmount = confirmTransactionAmount();
        int x = accountApplication.createTransaction(withdrawalAmount, "Withdrawal");
        if (x==0) successDialog("Withdrawal");
    }

    @FXML private void transferButtonAction(){
        transferAccounts.setVisible(true);
    }

    @FXML private void transferAccountAction(){
        String transferAmount = confirmTransactionAmount();
        Account selectedTransferAccount = transferAccounts.getSelectionModel().getSelectedItem();
        accountApplication.setTargetAccount(selectedTransferAccount);
        int x = accountApplication.createTransaction(transferAmount, "Transfer");
        if (x==0) successDialog("Transfer");
    }

    String confirmTransactionAmount(){
        TextInputDialog transactionDialog = new TextInputDialog();
        transactionDialog.showAndWait();
        return transactionDialog.getEditor().getText();
    }

    void successDialog(String transactionType){
        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
        successAlert.setContentText(transactionType + " completed successfully.");
        successAlert.setHeaderText("Transaction Complete");
        successAlert.showAndWait();
    }

    void addTransactionToTable(Transaction completedTransaction){
        transactionList.add(completedTransaction);
        transactionTableView.getItems().add(completedTransaction);
        transactionTableView.refresh();
    }
}

