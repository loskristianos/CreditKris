package gui;

import account.Account;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import transaction.Transaction;

public class TransactionApplication extends Application {
    Account account;
    Transaction transaction;
    TransactionController transactionController;

    public TransactionApplication(Transaction transaction, Account account){
        this.transaction = transaction;
        this.account = account;
    }

    TransactionController createTransactionController(){
        return new TransactionController();
    }

    @Override
    public void start(Stage stage) throws Exception {
        transactionController = createTransactionController();
        transactionController.setTransactionApplication(this);
        transactionController.setTransaction(transaction);
        transactionController.setAccount(account);
        transactionController.setCustomerName(customerNameLookup());
        transactionController.setCurrentStage(stage);
        FXMLLoader fxmlloader =new FXMLLoader();
        fxmlloader.setController(transactionController);
        fxmlloader.setLocation(getClass().getResource("transaction-view.fxml"));
        Scene scene = new Scene(fxmlloader.load());
        stage.setTitle("Transaction Details");
        stage.setScene(scene);
        stage.showAndWait();
    }

    String customerNameLookup(){
       return transaction.getInitiatingCustomer().getFullName();
    }
}
