/*
    I am putting this in again for now - I think I'll have all the view classes produce
    HashMaps from their fields and take HashMaps as inputs to display, and this will handle
    that in/out process, convert them to the relevant DataObject and invoke the main controller
    (which probably needs a new name now)
 */
package controller;

import account.Account;
import customer.Customer;
import interfaces.*;
import view.*;


import java.util.ArrayList;
import java.util.Arrays;
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
        View x = new LoginView();
        x.displayView();
        HashMap<String, String> details = x.getViewFields();
        DataObject newlogin = objectCreator.createLoginObject(details.get("username"), details.get("password"));
        DataObject customer = controller.loginAttempt(newlogin);
        if (customer != null) {
            mainMenuView(customer);
        } else System.out.println("Not found.");
    }

    public void mainMenuView(DataObject customer) {
        View x = new MainMenuView(customer);
        x.displayView();
        String menuChoice = x.getSelectedOption();
        switch (menuChoice) {
            case "editDetails":
                System.out.println("to be implemented");break;
            case "logOut": System.exit(0);break;
            case "manageAccounts": {
                List<DataObject> accountList = controller.getCustomerAccounts((Customer)customer);
                accountsView(accountList,customer);break;
            }
        }
    }

    public void accountsView(List<DataObject> accountList,DataObject customer){
        View x = new AccountsView(accountList,customer);
        x.displayView();
        String menuChoice = x.getSelectedOption();
        DataObject account = null;
        for (DataObject object : accountList) {
            if (menuChoice == object.getDetails().get("accountNumber")) {
                int i = accountList.indexOf(object);
                account = accountList.get(i);
            }
        }

        Account accountObject = (Account) account;
        List <DataObject> transactionList = controller.getAccountTransactions(accountObject);
        transactionsView(transactionList,accountObject);
    }

    public void transactionsView(List<DataObject> transactionList, DataObject account){
        View x = new TransactionsView(transactionList,account);
        x.displayView();
        String menuChoice = x.getSelectedOption();
        System.out.println(menuChoice);
    }

    public void newTransactionView(){    }

    public void newCustomerView(){      }

    public void createCustomerView(){}
    public void editCustomerView(HashMap<String,String> inputMap){}

}

