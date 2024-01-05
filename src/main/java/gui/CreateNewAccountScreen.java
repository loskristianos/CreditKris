package gui;

import customer.Customer;

public class CreateNewAccountScreen {
    Customer customer;

    public CreateNewAccountScreen(Customer inputCustomer) {
        this.customer = inputCustomer;
    }

    public void displayScreen(){
        start();
    }

    public void start(){
        // only needs account type selection and a way to input signatories if required
        // - everything else is set automatically.

    }
}
