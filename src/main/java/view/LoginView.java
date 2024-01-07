package view;


import customer.Customer;
import gui.LoginScreen;
import login.LoginObject;

public class LoginView extends View {

    @Override
    public void displayView() {
        new LoginScreen().displayScreen();
    }

    public void loginAttempt(String username, String password){
        LoginObject loginObject = new LoginObject(username, password);
        Customer returnedCustomer = loginObject.loginAttempt();
        if (returnedCustomer != null ){
            new CustomerView(returnedCustomer).displayView();
        } else {
            System.out.println("failed");
            // display error/ launch new customer Screen (to do)
        }
    }
}
