package interfaces;

import database.*;
import login.LoginObject;

import java.util.ArrayList;
import java.util.List;

public class DataHandlerCreator implements DataHandling{

    public DataHandler createLoginDataHandler(DataObject inputObject) {
       return new LoginDataHandler((LoginObject) inputObject);
    }

    public DataHandler createCustomerDataHandler(DataObject inputObject) {
        return new CustomerDataHandler(inputObject);
    }

    public DataHandler createAccountDataHandler(DataObject inputObject) {
        return new AccountDataHandler(inputObject);
    }

    public DataHandler createTransactiontDataHandler(DataObject inputObject) {
        return new TransactionDataHandler(inputObject);
    }

    public DataHandler createAuthorisationDataHandler(DataObject inputObject) {
        return new AuthorisationDataHandler(inputObject);
    }

    public DataHandler createAuthorisationDataHandler(ArrayList<DataObject> inputList) {
        return new AuthorisationDataHandler(inputList);
    }

    public DataHandler createSignatoryDataHandler(DataObject inputObject) {
        return new SignatoryDataHandler(inputObject);
    }

    @Override
    public void writeNewRecord() {

    }

    @Override
    public List getRecords() {
        return null;
    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }
}
