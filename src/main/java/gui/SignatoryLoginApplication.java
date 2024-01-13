package gui;

import customer.Customer;
import login.LoginObject;

public class SignatoryLoginApplication extends LoginApplication {    // uses the existing LoginApplication, fxml and controller
                                                                        // but with a slight change...
    NewAccountApplication newAccountApplication;

    public SignatoryLoginApplication(){
        loginController = createLoginController();                      // we'll pass this class into loginController in place of
        loginController.setLoginApplication(this);                      // the original loginApplication, so when it calls loginApplication.loginAttempt
                                                                        // it will hit the method below instead of the one it would usually get...
    }

    public void setNewAccountApplication(NewAccountApplication newAccountApplication) {
        this.newAccountApplication = newAccountApplication;
    }

    int loginAttempt(String username, String password) {                // then instead of launching CustomerApplication with the
        LoginObject loginObject = new LoginObject(username, password);  // returnedCustomer we can send it back to the NewAccountApplication instead.
        Customer returnedCustomer = loginObject.loginAttempt();
        if (returnedCustomer != null ){
            newAccountApplication.addCustomerToSignatoryListView(returnedCustomer);
            currentStage.close();
            return 0;
        } else {
            return -1;
        }

    }
}
