/*
    I am putting this in again for now - I think I'll have all the view classes produce
    HashMaps from their fields and take HashMaps as inputs to display, and this will handle
    that in/out process, convert them to the relevant DataObject and invoke the main controller
    (which probably needs a new name now)
 */
package controller;

import customer.Customer;
import interfaces.*;


import java.util.HashMap;

public class ViewController {

    DataHandlerCreator dataHandlerCreator;
    DataObjectCreator objectCreator;


    Controller controller;

    public ViewController(){
        controller = new Controller(objectCreator,dataHandlerCreator);
    }


    public void launchView(String view) {
        // method to launch the view (either CLI or GUI)
        // called by main method, input string view controls what view is launched

    }

    public void loginView () {
        /*  display the loginPrompt ui class
            loginPrompt returns HashMap inputMap
        */
        //HashMap<String,String> inputMap = loginPrompt();
//        DataObject login = objectCreator.createLoginObject(inputMap.get("username"),inputMap.get("password"));
//        DataObject customer = controller.loginAttempt(login);
//        customerView(customer.getDetails());
    }

    public void customerView (HashMap<String,String> inputMap) {
        // display the customer menu screen
    }

    public void accountsView(){    }

    public void transactionsView(){    }

    public void newTransactionView(){    }

    public void newCustomerView(){      }


}

