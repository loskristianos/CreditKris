package gui;

import customer.Customer;
import javafx.stage.Stage;
import login.LoginObject;

import java.util.HashMap;

public class NewSignatoryApplication extends NewCustomerApplication {           // same as the other Signatory Application, we'll use all the existing
     NewAccountApplication newAccountApplication;                               // methods until the end where it would launch the CustomerApplication
                                                                                // and send the Customer back to the NewAccountApplication
    public NewSignatoryApplication(){
        newCustomerController = createNewCustomerController();
        newCustomerController.setNewCustomerApplication(this);
    }

    @Override
    public void start(Stage stage) throws Exception {
        super.start(stage);
        stage.showAndWait();
    }

    public void setNewAccountApplication(NewAccountApplication newAccountApplication){
        this.newAccountApplication = newAccountApplication;
    }

    int createCustomer(HashMap<String,String> loginDetails, HashMap<String,String> customerDetails) {
        LoginObject loginObject = new LoginObject(loginDetails.get("username"), loginDetails.get("password"));
        LoginObject newLogin = loginObject.write();
        Customer customer = new Customer(customerDetails);
        customer.setCustomerID(newLogin.getCustomerID());
        customer.write();
        newAccountApplication.addCustomerToSignatoryListView(customer);
        currentStage.close();
        return 0;
    }

}
