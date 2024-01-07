package view;

import account.Account;
import customer.Customer;
import gui.CreateNewTransactionScreen;

import interfaces.DataObjectCreator;
import transaction.DepositTransaction;
import transaction.Transaction;
import transaction.TransferTransaction;
import transaction.WithdrawalTransaction;

import java.util.HashMap;

public class CreateNewTransactionView extends View{
    Account account;
    Customer customer;
    String transactionType;
    Account targetAccount;

    public CreateNewTransactionView(){}

    public CreateNewTransactionView(Account account, Customer customer, String transactionType){
        this.account = account;
        this.customer = customer;
        this.transactionType = transactionType;
    }

    public CreateNewTransactionView(Account account, Customer customer, String transactionType, Account targetAccount){
        this.account = account;
        this.customer = customer;
        this.transactionType = transactionType;
        this.targetAccount = targetAccount;
    }
    @Override
    public void displayView() {
        if (transactionType.equals("Transfer"))
            new CreateNewTransactionScreen(account, customer, transactionType, targetAccount).displayScreen();
        else
            new CreateNewTransactionScreen(account, customer, transactionType).displayScreen();


    }

    public void createTransaction(String transactionAmount){
        Transaction newTransaction = switch (transactionType) {
            case "Deposit": yield new DepositTransaction(account,transactionAmount);
            case "Withdrawal": yield new WithdrawalTransaction(account,transactionAmount);
            case "Transfer": yield new TransferTransaction(account, targetAccount, transactionAmount);
            default: yield null;
        };
        assert newTransaction != null;
        newTransaction.writeData();
    }
}
