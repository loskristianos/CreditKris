package view;

import account.Account;
import controller.Controller;
import customer.Customer;
import gui.AccountScreen;
import interfaces.DataHandlerCreator;
import interfaces.DataObject;
import interfaces.DataObjectCreator;


import java.util.List;

public class AccountView extends View{

    Controller controller = new Controller(new DataObjectCreator(),new DataHandlerCreator());
    Account account;
    Customer customer;
    List<DataObject> accountList;
    List<DataObject> transactionList;

    public AccountView(Account inputAccount, Customer inputCustomer, List<DataObject> accountList){
        this.account = inputAccount;
        this.customer = inputCustomer;
        this.accountList = accountList;
        this.transactionList = controller.getAccountTransactions(account);
    }
    @Override
    public void displayView() {
        new AccountScreen(account,customer,transactionList,accountList).displayScreen();
    }
}
