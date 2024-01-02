/*
    I am putting this in again for now - I think I'll have all the view classes produce
    HashMaps from their fields and take HashMaps as inputs to display, and this will handle
    that in/out process, convert them to the relevant DataObject and invoke the main controller
    (which probably needs a new name now)
 */
package controller;

import customer.Customer;
import interfaces.*;
import login.LoginObject;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ViewController {

    DataHandlerCreator dataHandlerCreator = new DataHandlerCreator();
    DataObjectCreator objectCreator = new DataObjectCreator();


    Controller controller;

    public ViewController(){
        controller = new Controller(objectCreator,dataHandlerCreator);
    }


    public void loginView() {
            HashMap<String, String> loginDetails = new cli.LoginPrompt().displayPrompt();
            DataObject newlogin = objectCreator.createLoginObject(loginDetails.get("username"),loginDetails.get("password"));
            DataObject customer = controller.loginAttempt(newlogin);
            if (customer != null) {
                customerView(customer.getDetails());
            } else createCustomerView();
    }
    public void customerView (HashMap<String,String> inputMap) {
            String customerMenu = new cli.CustomerPrompt(inputMap).displayPrompt();
            switch (customerMenu) {
                case "editDetails": editCustomerView(inputMap);break;
                case "logOut": System.exit(0);break;
                case "manageAccounts": {Customer customer = (Customer) objectCreator.createNewCustomer(inputMap);
                List<DataObject> accountList = controller.getCustomerAccounts(customer);
                accountsView(accountList,inputMap);}
            }
    }

    public void createCustomerView(){}
    public void editCustomerView(HashMap<String,String> inputMap){}
    public void accountsView(List<DataObject> inputList, HashMap<String,String> inputMap){
        List<HashMap<String,String>> accountDetailList = new ArrayList<>();
        for (DataObject object : inputList) {
            accountDetailList.add(object.getDetails());
        }
    String accountSelection = new cli.AccountsPrompt(accountDetailList,inputMap).displayPrompt();
    }

    public void transactionsView(){    }

    public void newTransactionView(){    }

    public void newCustomerView(){      }


}

