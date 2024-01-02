package view;

import customer.Customer;
import interfaces.DataObject;

import java.util.HashMap;

public class CustomerView extends View {
    Customer customer;

    public CustomerView(DataObject inputObject) {
        this.customer = (Customer) inputObject;
        displayView();
    }

    @Override
    View displayView() {
        customer.getDetails();
        return null;
    }


    @Override
    HashMap<String, String> getViewFields() {
        return null;
    }

    @Override
    DataObject createObject(HashMap<String, String> inputMap) {
        return null;
    }
}
