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

    public HashMap<String,String> loginView (HashMap<String,String> inputMap) {
        DataObject login = objectCreator.createLoginObject(inputMap.get("username"),inputMap.get("password"));
        DataObject customer = controller.loginAttempt(login);
        return customer.getDetails();
    }

    public HashMap<String,String> customerView (HashMap<String,String> inputMap) {
        return inputMap;
    }
}
