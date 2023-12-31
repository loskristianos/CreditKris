package interfaces;

import database.*;
import login.LoginObject;

import java.util.List;

public class DataHandlerCreator {

    public DataHandler createLoginDataHandler(DataObject inputObject) {
       return new LoginDataHandler((LoginObject) inputObject);
    }

    public DataHandler createCustomerDataHandler(DataObject inputObject) {
        return new CustomerDataHandler(inputObject);
    }

    public DataHandler createAccountDataHandler(DataObject inputObject) {
        return new AccountDataHandler(inputObject);
    }

    public DataHandler createTransactionDataHandler(DataObject inputObject) {
        return new TransactionDataHandler(inputObject);
    }

    public DataHandler createAuthorisationDataHandler(DataObject inputObject) {
        return new AuthorisationDataHandler(inputObject);
    }

    public DataHandler createAuthorisationDataHandler(List<DataObject> inputList) {
        return new AuthorisationDataHandler(inputList);
    }

    public DataHandler createSignatoryDataHandler(DataObject inputObject) {
        return new SignatoryDataHandler(inputObject);
    }
}
