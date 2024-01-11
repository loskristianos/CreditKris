package gui;

import customer.Customer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import login.LoginObject;

import java.util.HashMap;

public class NewCustomerApplication extends Application {

    NewCustomerController newCustomerController;
    Stage currentStage;

    public NewCustomerApplication(){
        newCustomerController = createNewCustomerController();
        newCustomerController.setNewCustomerApplication(this);
    }
    private NewCustomerController createNewCustomerController(){
        return new NewCustomerController();
    }
    @Override
    public void start(Stage stage) throws Exception {
        newCustomerController.setCurrentStage(stage);
        currentStage = stage;
        FXMLLoader fxmlloader =new FXMLLoader();
        fxmlloader.setController(newCustomerController);
        fxmlloader.setLocation(getClass().getResource("newCustomer-view.fxml"));
        Scene scene = new Scene(fxmlloader.load());
        stage.setTitle("Enter Customer Details");
        stage.setScene(scene);
    }

    int createCustomer(HashMap<String,String> loginDetails, HashMap<String,String> customerDetails) throws Exception {
        LoginObject loginObject = new LoginObject(loginDetails.get("username"), loginDetails.get("password"));
        LoginObject newLogin = loginObject.write();
        Customer customer = new Customer(customerDetails);
        customer.setCustomerID(newLogin.getCustomerID());
        customer.write();
        new CustomerApplication(customer).start(currentStage);
        return 0;
    }
}
