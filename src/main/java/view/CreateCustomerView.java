package view;

import controller.Controller;
import customer.Customer;
import gui.CreateCustomerScreen;
import interfaces.DataHandlerCreator;
import interfaces.DataObjectCreator;
import login.LoginObject;

import java.util.HashMap;

public class CreateCustomerView extends View{
    Controller controller = new Controller(new DataObjectCreator(),new DataHandlerCreator());
    @Override
    public void displayView() {
        new CreateCustomerScreen().displayScreen();
    }

    public void createCustomer(HashMap<String,String> loginDetails,HashMap<String,String> customerDetails){
        LoginObject loginObject = new DataObjectCreator().createLoginObject(loginDetails.get("username"), loginDetails.get("password"));
        Customer customer = new DataObjectCreator().createNewCustomer(customerDetails);
        new CreateNewAccountView(customer).displayView();
    }
}
