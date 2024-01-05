package view;

import customer.Customer;
import gui.CreateNewAccountScreen;

public class CreateNewAccountView extends View{
    Customer customer;
    public CreateNewAccountView(Customer inputCustomer){
        this.customer = inputCustomer;
    }
    @Override

    public void displayView() {
        new CreateNewAccountScreen(customer).displayScreen();
    }
}
