package view;

import controller.Controller;
import gui.LoginScreen;
import interfaces.DataHandlerCreator;
import interfaces.DataObject;
import interfaces.DataObjectCreator;
import javafx.application.Application;
import login.LoginObject;

public class LoginView extends View {
    Controller controller = new Controller(new DataObjectCreator(), new DataHandlerCreator());

    @Override
    public void displayView() {
        new LoginScreen().displayScreen();
    }

    // need some sort of method to listen for submit from LoginScreen? or button calls this method directly?
    public void loginAttempt(String username, String password){
        LoginObject loginObject = new DataObjectCreator().createLoginObject(username, password);
        DataObject returnedCustomer = controller.loginAttempt(loginObject);
        if (returnedCustomer != null ){
            new CustomerView(returnedCustomer).displayView();
        } else {
            System.out.println("failed");
            // display error/ launch new customer Screen (to do)
        }
    }
}
