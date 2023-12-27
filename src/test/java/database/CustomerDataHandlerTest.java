package database;
import account.Account;
import account.ClientAccount;
import customer.Customer;
import interfaces.DataObject;
import login.LoginObject;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CustomerDataHandlerTest {

    HashMap<String,String > details = new HashMap<>() {
        {
            put("customerID", "9009");
            put("firstName", "STRfirstName");
            put("lastName", "STRlast");
            put("dob", "02/09/1976");
            put("address1", "19 Albion Close");
            put("address2", null);
            put("addressTown", "Liverpool");
            put("addressPostcode", "L3 4BQ");
        }};

    @Test
    void insertNewCustomer() {
        Customer x = new Customer(details);
        DataHandler y = new CustomerDataHandler(x);
        y.writeNewRecord();
    }

    @Test
    void getCustomerFromLogin() {
        LoginObject s = new LoginObject("queryTest","queryTestPass");
        s.setCustomerID("9009");
        DataHandler q = new CustomerDataHandler(s);
        List<DataObject> sd = q.getRecords();
        for (DataObject dataObject : sd) {
            System.out.println(dataObject.getDetails().get("firstName"));
        }
    }

    @Test
    void getCustomersFromAccount() {
        HashMap<String,String> testDataHM = new HashMap<>(Map.of("customerID","123", "accountNumber","57", "currentBalance","308.50", "overdraftLimit","500", "signatories","1"));
        Account acc = new ClientAccount(testDataHM);
        DataHandler gge = new CustomerDataHandler(acc);
        List<DataObject> cst = gge.getRecords();
        for (DataObject dataObject : cst) {
            System.out.println(dataObject.getDetails().get("firstName"));
        }
    }
}