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

    @FXML private void backButtonAction(){
        currentStage.setScene(previousScene);
    }

    @FXML private void depositButtonAction(){
        String depositAmount = confirmTransactionAmount();
    }

    @FXML private void withdrawalButtonAction(){
        String withdrawalAmount = confirmTransactionAmount();
    }

    @FXML private void transferButtonAction(){
        transferAccounts.setVisible(true);
    }

    @FXML private void transferAccountAction(){
        String transferAmount = confirmTransactionAmount();
    }

    String confirmTransactionAmount(){
        TextInputDialog transactionDialog = new TextInputDialog();
        transactionDialog.showAndWait();
        return transactionDialog.getEditor().getText();
    }

}

