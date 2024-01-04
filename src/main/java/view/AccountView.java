package view;

import account.Account;
import controller.Controller;
import customer.Customer;
import gui.AccountScreen;
import interfaces.DataHandlerCreator;
import interfaces.DataObject;
import interfaces.DataObjectCreator;
import javafx.application.Application;

import java.util.List;

public class AccountView extends View{

    Controller controller = new Controller(new DataObjectCreator(),new DataHandlerCreator());
    Account account;
    Customer customer;
    List<DataObject> transactionList;

    public AccountView(Account inputAccount, Customer inputCustomer){
        this.account = inputAccount;
        this.customer = inputCustomer;
        transactionList = controller.getAccountTransactions(account);
    }
    @Override
    public void displayView() {
        Application accountScreen = new AccountScreen(account,customer,transactionList);
        Application.launch();
    }
}
