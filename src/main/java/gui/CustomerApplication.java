package gui;

import account.Account;
import account.ClientAccount;
import account.CommunityAccount;
import account.SmallBusinessAccount;
import customer.Customer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import transaction.PendingTransaction;

import java.util.List;
import java.util.StringJoiner;

public class CustomerApplication extends Application {
    Customer customer;
    CustomerController customerController;
    Stage currentStage;
    Scene currentScene;
    List<Account> accountList;
    List<PendingTransaction> pendingTransactionList;

    public CustomerApplication(Customer customer){
        this.customer = customer;
        accountList = customer.getAccounts();
        customerController = createCustomerController();
        customerController.setCustomer(customer);
        customerController.setAccountList(accountList);
        customerController.setCustomerApplication(this);
    }
    public CustomerController createCustomerController(){
        return new CustomerController();
    }

    @Override
    public void start(Stage stage) throws Exception {
        customerController.setCurrentStage(stage);
        currentStage = stage;
        FXMLLoader fxmlloader =new FXMLLoader();
        fxmlloader.setController(customerController);
        fxmlloader.setLocation(getClass().getResource("customer-view.fxml"));
        Scene scene = new Scene(fxmlloader.load());
        customerController.setCurrentScene(scene);
        currentScene = scene;
        stage.setTitle("Customer Accounts");
        stage.setScene(scene);
    }

    void createNewAccount(String accountType) throws Exception{
        Account newAccount = switch (accountType) {
            case "Client": yield new ClientAccount(customer);
            case "Community": yield new CommunityAccount(customer);
            case "Business": yield new SmallBusinessAccount(customer);
            default: yield null;
        };
        if (newAccount != null) {
            NewAccountApplication newAccountApplication = new NewAccountApplication(customer,newAccount);
            newAccountApplication.setCustomerApplication(this);
            newAccountApplication.start(new Stage());
        }
    }

    void addAccountToList(Account returnedAccount){
        customerController.addAccountToTable(returnedAccount);
    }

    void selectAccount(Account selectedAccount) throws Exception{
        AccountApplication accountApplication = new AccountApplication(selectedAccount,customer);
        accountApplication.setCustomerApplication(this);
        accountApplication.setAccountList(accountList);
        accountApplication.setPreviousScene(currentScene);
        accountApplication.start(currentStage);
    }

    void checkPendingTransactions(){
        pendingTransactionList = customer.getPendingTransactions();
        if (pendingTransactionList != null) {
            StringJoiner accountsWithPendingTransactions = new StringJoiner("\n", "\n", "\n\n");
            for (PendingTransaction transaction : pendingTransactionList) {
                if (!accountsWithPendingTransactions.toString().contains(transaction.getAccountNumber())) {
                    accountsWithPendingTransactions.add(transaction.getAccountNumber());
                }
            }
            if (!accountsWithPendingTransactions.toString().isBlank()) {
                customerController.pendingTransactionAlert(accountsWithPendingTransactions.toString());
            }
        }
    }

    public void refreshData(){
        customerController.refreshData();
    }
}