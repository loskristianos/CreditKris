package gui;

import customer.Customer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import login.LoginObject;

import java.util.HashMap;
import java.util.Map;

public class NewCustomerApplication extends Application {

    NewCustomerController newCustomerController;
    Stage currentStage;

    public NewCustomerApplication(){
        newCustomerController = createNewCustomerController();
        newCustomerController.setNewCustomerApplication(this);
    }
    NewCustomerController createNewCustomerController(){
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

    int verifyInputFields(HashMap<String,String> login, HashMap<String,String> customerDetails){
        // check login and password aren't blank
        for (Map.Entry<String,String> entry : login.entrySet()){
            if(entry.getValue()==null || entry.getValue().isBlank()){
                return -1;
            }
        }
        // check none of the customer detail fields are blank (except for address2 which is allowed to be blank)
        for (Map.Entry<String,String> entry : customerDetails.entrySet()){
            if(entry.getValue()==null || entry.getValue().isBlank()){
                if (!entry.getKey().equals("address2")) return -1;
            }
        } return 0;
    }

    int verifyPasswordMatch(String password, String confirmPassword){
        if (!password.equals(confirmPassword))
            return -1;
        else
            return 0;
    }
}
