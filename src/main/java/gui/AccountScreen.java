package gui;

import account.Account;
import customer.Customer;

public class AccountScreen {
    Account account;
    Customer customer;

    public AccountScreen(Account inputAccount, Customer inputCustomer){
        this.account = inputAccount;
        this.customer = inputCustomer;
    }

}
