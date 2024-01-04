package view;

import gui.CustomerScreen;
import interfaces.DataObject;
import javafx.application.Application;

import java.util.List;

public class CustomerView extends View {
    DataObject customer;
    List<DataObject> accounts;

    public CustomerView(List<DataObject> inputList, DataObject inputObject) {
        this.customer = inputObject;
        this.accounts = inputList;
        displayView();
    }

    @Override
    public void displayView() {
        Application customerScreen = new CustomerScreen(accounts,customer);
        Application.launch();
    }

}