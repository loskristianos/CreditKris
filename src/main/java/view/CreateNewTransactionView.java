package view;

import account.Account;
import controller.Controller;
import customer.Customer;
import gui.CreateNewTransactionScreen;
import interfaces.DataHandlerCreator;
import interfaces.DataObjectCreator;
import transaction.Transaction;

import java.util.HashMap;

public class CreateNewTransactionView extends View{
    Controller controller = new Controller(new DataObjectCreator(),new DataHandlerCreator());
    Account account;
    Customer customer;
    String transactionType;
    String additionalInfo;

    public CreateNewTransactionView(){}

    public CreateNewTransactionView(Account account, Customer customer, String transactionType){
        this.account = account;
        this.customer = customer;
        this.transactionType = transactionType;
    }

    public CreateNewTransactionView(Account account, Customer customer, String transactionType, String additionalInfo){
        this.account = account;
        this.customer = customer;
        this.transactionType = transactionType;
        this.additionalInfo = additionalInfo;
    }
    @Override
    public void displayView() {
        if (additionalInfo==null)
        new CreateNewTransactionScreen(account, customer, transactionType).displayScreen();
        else
            new CreateNewTransactionScreen(account,customer,transactionType,additionalInfo).displayScreen();
    }

    public void createTransaction(HashMap<String,String> transactionDetails){
        Transaction newTransaction = new DataObjectCreator().createNewTransaction(transactionDetails);
        controller.newTransaction(newTransaction);
    }
}
