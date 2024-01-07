package view;

import account.Account;
import customer.Customer;
import gui.AccountScreen;
import transaction.Transaction;

import java.util.List;

public class AccountView extends View{

    Account account;
    Customer customer;
    List<Account> accountList;
    List<Transaction> transactionList;

    public AccountView(Account inputAccount, Customer inputCustomer, List<Account> inputAccountList){
        account = inputAccount;
        customer = inputCustomer;
        accountList = inputAccountList;
        transactionList = account.getTransactions();
    }
    @Override
    public void displayView() {
        new AccountScreen(account,customer,transactionList,accountList).displayScreen();
    }
}
