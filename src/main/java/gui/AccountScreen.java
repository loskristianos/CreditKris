package gui;

import account.Account;
import customer.Customer;
import interfaces.DataObject;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.List;

public class AccountScreen extends Application {
    Account account;
    Customer customer;
    List<DataObject> transactionList;

    public AccountScreen(Account inputAccount, Customer inputCustomer, List<DataObject> inputList){
        this.account = inputAccount;
        this.customer = inputCustomer;
        this.transactionList = inputList;
    }

    @Override
    public void start(Stage stage) throws Exception {

    }
}
