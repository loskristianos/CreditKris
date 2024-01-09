package view;

import account.Account;
import customer.Customer;
import gui.CustomerScreen;

import java.util.List;

public class CustomerView extends View {

    Customer customer;
    List<Account> accountsList;

    public CustomerView(Customer inputCustomer) {
        customer = inputCustomer;
        accountsList = customer.getAccounts();
    }

    @Override
    public void displayView() {
        new CustomerScreen(accountsList,customer).displayScreen();
    }

}