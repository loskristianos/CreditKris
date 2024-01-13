package gui;

import account.Account;
import account.ClientAccount;
import account.CommunityAccount;
import account.SmallBusinessAccount;
import customer.Customer;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


import java.util.List;

public class CustomerController {

    Customer customer;
    List<Account> accountList;
    Stage currentStage;
    Scene currentScene;
    CustomerApplication customerApplication;

    @FXML private TextField customerNameDisplay;
    @FXML private TextField dobDisplay;
    @FXML private TextField customerIdDisplay;
    @FXML private TextArea customerAddressDisplay;
    @FXML private Button clientButton;
    @FXML private Button businessButton;
    @FXML private Button communityButton;
    @FXML private TableView<Account> accountsTable;
    @FXML private TableColumn<Account,String> accountNumberColumn;
    @FXML private TableColumn<Account,String> accountTypeColumn;
    @FXML private TableColumn<Account,String> currentBalanceColumn;
    @FXML private TableColumn<Account,String> overdraftLimitColumn;


    public CustomerController(){
    }
    public void setCustomer(Customer customer){
        this.customer = customer;
    }
    public void setCurrentStage(Stage stage){
        currentStage = stage;
    }
    public void setCurrentScene(Scene scene){
        currentScene = scene;
    }
    public void setCustomerApplication(CustomerApplication customerApplication) {
        this.customerApplication = customerApplication;
    }
    public void setAccountList(List<Account> accountList){
        this.accountList = accountList;
    }
    @FXML private void initialize(){
        customerNameDisplay.setText(customer.getFullName());
        dobDisplay.setText(customer.getDob());
        customerIdDisplay.setText(customer.getCustomerID());
        customerAddressDisplay.setText(customer.getFullAddress());


        accountNumberColumn.setCellValueFactory(new PropertyValueFactory<>("accountNumber"));
        accountTypeColumn.setCellValueFactory(new PropertyValueFactory<>("accountType"));
        currentBalanceColumn.setCellValueFactory(new PropertyValueFactory<>("currentBalance"));
        overdraftLimitColumn.setCellValueFactory(new PropertyValueFactory<>("overdraftLimit"));

        for (Account account : accountList){
            accountsTable.getItems().add(account);
            switch (account.getAccountType()){
                case "Client": clientButton.setVisible(false); break;
                case "Community": communityButton.setVisible(false); break;
                case "Business": businessButton.setVisible(false); break;
            }
        }

        customerApplication.checkPendingTransactions();
    }

    @FXML void newClientButtonAction() throws Exception{
        customerApplication.createNewAccount("Client");
    }

    @FXML void newCommunityButtonAction() throws Exception {
        customerApplication.createNewAccount("Community");
    }

    @FXML void newBusinessButtonAction() throws Exception {
        customerApplication.createNewAccount("Business");
    }

    @FXML void logOutButtonAction() throws Exception{
        new LoginApplication().start(currentStage);
    }
    void addAccountToTable(Account completedAccount){
        accountList.add(completedAccount);
        accountsTable.getItems().add(completedAccount);
        accountsTable.refresh();
    }

    void pendingTransactionAlert(String accountsWithPendingTransactions){
        Alert pendingTransactionsAlert = new Alert(Alert.AlertType.INFORMATION);
        pendingTransactionsAlert.setContentText("You have new transactions awaiting authorisation for the following accounts:\n" + accountsWithPendingTransactions
                + "Select an account from the main Customer Accounts screen to view and authorize transactions.");
        pendingTransactionsAlert.show();
    }

    @FXML private void accountsTableSelection() throws Exception{
        Account selectedAccount = accountsTable.getSelectionModel().getSelectedItem();
        if (selectedAccount != null) customerApplication.selectAccount(selectedAccount);
    }

}
