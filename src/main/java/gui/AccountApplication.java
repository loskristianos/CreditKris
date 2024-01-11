package gui;

import account.Account;
import customer.Customer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import transaction.Transaction;

import java.util.List;

public class AccountApplication extends Application {
    Account account;
    Customer customer;
    List<Transaction> transactionList;
    List<Account> accountList;
    AccountController accountController;
    Scene previousScene;
    public AccountApplication(Account inputAccount, Customer inputCustomer, List<Account> inputAccountList){
        account = inputAccount;
        customer = inputCustomer;
        transactionList = account.getTransactions();
        accountList = inputAccountList;
        accountController = createAccountController();
    }

    public AccountController createAccountController(){
        return new AccountController();
    }

    public void setPreviousScene(Scene scene) {
        previousScene = scene;
    }
    @Override
    public void start(Stage stage) throws Exception {
        accountController.setCurrentStage(stage);
        accountController.setAccount(account);
        accountController.setCustomer(customer);
        accountController.setAccountList(accountList);
        accountController.setTransactionList(transactionList);
        accountController.setPreviousScene(previousScene);
        FXMLLoader fxmlloader =new FXMLLoader();
        fxmlloader.setController(accountController);
        fxmlloader.setLocation(getClass().getResource("account-view.fxml"));
        Scene scene = new Scene(fxmlloader.load());
        stage.setTitle("Account Details");
        stage.setScene(scene);
    }
}