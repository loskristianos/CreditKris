package gui;

import account.Account;
import customer.Customer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import transaction.*;

import java.util.List;

public class AccountApplication extends Application {
    Account account;
    Customer customer;
    List<Transaction> transactionList;
    List<PendingTransaction> pendingTransactionList;
    List<Account> accountList;
    AccountController accountController;
    CustomerApplication customerApplication;
    Scene previousScene;
    Stage currentStage;
    Account targetAccount;
    public AccountApplication(Account inputAccount, Customer inputCustomer){
        account = inputAccount;
        customer = inputCustomer;

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
    public void setCustomerApplication(CustomerApplication customerApplication){
        this.customerApplication = customerApplication;
    }
    @Override
    public void start(Stage stage) throws Exception {
        currentStage = stage;
        setFields();
        pendingTransactionList = pendingTransactionsForCustomerAndAccount();
        accountController.setPendingTransactionList(pendingTransactionList);
        accountController.setPreviousScene(previousScene);
        FXMLLoader fxmlloader =new FXMLLoader();
        fxmlloader.setController(accountController);
        fxmlloader.setLocation(getClass().getResource("account-view.fxml"));
        Scene scene = new Scene(fxmlloader.load());
        stage.setTitle("Account Details");
        stage.setScene(scene);
        if (!pendingTransactionList.isEmpty()) {
            PendingTransactionApplication pendingTransactionApplication = new PendingTransactionApplication(pendingTransactionList);
            pendingTransactionApplication.setAccountController(accountController);
            pendingTransactionApplication.setAccountApplication(this);
            pendingTransactionApplication.start(new Stage());
        }
    }

    void setFields(){
        transactionList = account.getTransactions();
        accountController = createAccountController();
        accountController.setTransactionList(transactionList);
        accountController.setAccountApplication(this);
        accountController.setAccount(account);
        accountController.setCustomer(customer);
        accountController.setAccountList(accountList);
        accountController.setTransactionList(transactionList);
        accountController.setCurrentStage(currentStage);
    }

    List<PendingTransaction> pendingTransactionsForCustomerAndAccount(){
        List<PendingTransaction> allPendingForCustomer = customer.getPendingTransactions();
        for (PendingTransaction transaction : allPendingForCustomer){
            if(!transaction.getAccountNumber().equals(account.getAccountNumber())){
                allPendingForCustomer.remove(transaction);
            }
        } return allPendingForCustomer;
    }

    int createTransaction(String transactionAmount, String transactionType){
         Transaction newTransaction = switch (transactionType) {
             case "Deposit": yield new DepositTransaction(account,transactionAmount);
             case "Withdrawal": yield new WithdrawalTransaction(account,transactionAmount);
             case "Transfer": yield new TransferTransaction(account, targetAccount, transactionAmount);
             default: yield null;
         };
         if (newTransaction == null) {return -1;}
         newTransaction.setCustomerID(customer.getCustomerID());
         switch(newTransaction.writeData()) {
             case 0: {
                 Transaction completedTransaction = newTransaction.getThisTransaction();
                 accountController.addTransactionToTable(completedTransaction);
                 return 0;
             }
             case -2: return -2;
             case -3: return -3;
             default: return -1;
         }
    }

    public void selectTransaction(Transaction selectedTransaction) throws Exception{
        TransactionApplication transactionApplication = new TransactionApplication(selectedTransaction, account);
        transactionApplication.start(new Stage());
    }

    public void back(){
        currentStage.setScene(previousScene);
        customerApplication.refreshData();
    }

    public void reload(){
        try {start(currentStage);}
        catch (Exception exception){}
    }
}