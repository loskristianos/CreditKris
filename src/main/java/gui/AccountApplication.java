package gui;

import account.Account;
import customer.Customer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import transaction.DepositTransaction;
import transaction.Transaction;
import transaction.TransferTransaction;
import transaction.WithdrawalTransaction;

import java.util.List;

public class AccountApplication extends Application {
    Account account;
    Customer customer;
    List<Transaction> transactionList;
    List<Account> accountList;
    AccountController accountController;
    Scene previousScene;
    Account targetAccount;
    public AccountApplication(Account inputAccount, Customer inputCustomer){
        account = inputAccount;
        customer = inputCustomer;
        transactionList = account.getTransactions();
        accountController = createAccountController();
        accountController.setTransactionList(transactionList);
        accountController.setAccountApplication(this);
    }

    public AccountController createAccountController(){
        return new AccountController();
    }
    public void setAccountList(List<Account> accountList){
        this.accountList = accountList;
    }

    public void setPreviousScene(Scene scene) {
        previousScene = scene;
    }

    public void setTargetAccount(Account account){
        targetAccount = account;
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

    int createTransaction(String transactionAmount, String transactionType){
     Transaction newTransaction = switch (transactionType) {
         case "Deposit": yield new DepositTransaction(account,transactionAmount);
         case "Withdrawal": yield new WithdrawalTransaction(account,transactionAmount);
         case "Transfer": yield new TransferTransaction(account, targetAccount, transactionAmount);
         default: yield null;
     };
     if (newTransaction == null) return -1;
     newTransaction.setCustomerID(customer.getCustomerID());
     newTransaction.writeData();
     Transaction completedTransaction = newTransaction.getThisTransaction();
     accountController.addTransactionToTable(completedTransaction);
     return 0;
    }
}