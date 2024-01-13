package view;


import customer.Customer;
import oldgui.CreateCustomerScreen;
import login.LoginObject;

import java.util.HashMap;

public class CreateCustomerView extends View{
    @Override
    public void displayView() {
        new CreateCustomerScreen().displayScreen();
    }

    public void createCustomer(HashMap<String,String> loginDetails,HashMap<String,String> customerDetails){
        LoginObject loginObject = new LoginObject(loginDetails.get("username"), loginDetails.get("password"));
        LoginObject newLogin = loginObject.write();
        Customer customer = new Customer(customerDetails);
        customer.setCustomerID(newLogin.getCustomerID());
        customer.write();
        new CustomerView(customer).displayView();
    }
}
