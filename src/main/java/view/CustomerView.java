package view;

import gui.CustomerScreen;
import interfaces.DataObject;

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
        CustomerScreen customerScreen = new CustomerScreen(accounts,customer);
        customerScreen.launch();
    }

}