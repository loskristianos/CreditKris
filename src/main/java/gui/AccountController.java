package gui;

import account.Account;
import customer.Customer;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import transaction.PendingTransaction;
import transaction.Transaction;

import java.util.List;

public class AccountController {
    Stage currentStage;
    Scene previousScene;
    Account account;
    Customer customer;
    List<Transaction> transactionList;
    List<PendingTransaction> pendingTransactionList;
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

        // sut up ComboBox to list available accounts for transfer
        transferAccounts.setCellFactory(new Callback<ListView<Account>, ListCell<Account>>() {
            @Override
            public ListCell<Account> call(ListView<Account> accountListView) {
                return new ListCell<Account>() {
                    public void updateItem(Account item, boolean empty){
                        super.updateItem(item, empty);
                        if (item == null || empty) {setGraphic(null);}
                        else {setText(item.getAccountNumber() + ": " + item.getAccountType() + " account");
                    }
                }
            };
        }});

        for (Account account : accountList){
            if (!account.getAccountNumber().equals(this.account.getAccountNumber()))
                transferAccounts.getItems().add(account);
        }
        // set up transaction TableView
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
    public void setPendingTransactionList(List<PendingTransaction> pendingTransactionList){
        this.pendingTransactionList = pendingTransactionList;
    }

    public void setAccountApplication(AccountApplication accountApplication){
        this.accountApplication = accountApplication;
    }

    @FXML private void backButtonAction(){
        accountApplication.back();
    }

    @FXML private void depositButtonAction(){
        doTransaction("Deposit");
    }

    @FXML private void withdrawalButtonAction(){
        doTransaction("Withdrawal");
    }
    @FXML private void transferButtonAction(){
        transferAccounts.setVisible(true);
    }

    @FXML private void transferAccountAction(){
        Account selectedTransferAccount = transferAccounts.getSelectionModel().getSelectedItem();
        accountApplication.setTargetAccount(selectedTransferAccount);
        doTransaction("Transfer");
    }
    private void doTransaction(String transactionType){
        String transactionAmount = confirmTransactionAmount(transactionType);
        if (transactionAmount != null && !transactionAmount.isBlank()) {
            int x = accountApplication.createTransaction(transactionAmount, transactionType);
            switch (x) {
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
    }

    void addTransactionToTable(Transaction completedTransaction){
        transactionList.add(completedTransaction);
        currentBalanceDisplay.setText(account.getCurrentBalance());
        transactionTableView.getItems().add(completedTransaction);
        transactionTableView.refresh();
    }

    @FXML private void transactionTableSelection() throws Exception{
        Transaction selectedTransaction = transactionTableView.getSelectionModel().getSelectedItem();
        if (selectedTransaction != null) accountApplication.selectTransaction(selectedTransaction);
    }

    String confirmTransactionAmount(String transactionType){
        TextInputDialog transactionDialog = new TextInputDialog();
        transactionDialog.setTitle("New " +transactionType);
        transactionDialog.setHeaderText("Please enter the amount of your " +transactionType.toLowerCase()+".");
        transactionDialog.showAndWait();
        return transactionDialog.getEditor().getText();
    }

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

