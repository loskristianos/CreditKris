package view;

import cli.MainMenuPrompt;
import customer.Customer;
import interfaces.DataObject;
import interfaces.UI;

import java.util.HashMap;

public class MainMenuView extends View {
    Customer customer;
    UI uiType;

    public MainMenuView(DataObject inputObject) {
        this.customer = (Customer) inputObject;
        this.uiType = new MainMenuPrompt(customer.getDetails());
    }

    @Override
    public void displayView() {
        uiType.displayPrompt();
    }

    public String getSelectedOption(){
        return uiType.optionSelection();
    }

    @Override
    public HashMap<String, String> getViewFields() {
        return uiType.getInputDetails();
    }

    @Override
    public DataObject createObject(HashMap<String, String> inputMap) {
        return null;
    }
}
