package view;

import controller.Controller;
import customer.Customer;
import gui.CustomerScreen;
import interfaces.DataHandlerCreator;
import interfaces.DataObject;
import interfaces.DataObjectCreator;


import java.util.List;

public class CustomerView extends View {
    Controller controller = new Controller(new DataObjectCreator(),new DataHandlerCreator());
    Customer customer;
    List<DataObject> accountsList;

    public CustomerView(DataObject inputCustomer) {
        this.customer = (Customer) inputCustomer;
        this.accountsList = controller.getCustomerAccounts(customer);
    }

    @Override
    public void displayView() {
        new CustomerScreen(accountsList,customer).displayScreen();
    }

}