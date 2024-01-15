package gui;

import account.Account;
import customer.Customer;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class NewAccountApplicationTest {

    HashMap<String, String> testCustomerDetails = new HashMap<>(Map.of("customerID", "432", "firstName", "Rodney", "lastName", "Price", "dob", "12/06/1972", "address1", "1 The Street", "address2", "The Village", "addressTown", "Townsville", "addressPostcode", "AB1 2CD"));
    HashMap<String,String> testCustomerDetails2 = new HashMap<>(Map.of("customerID","456", "firstName","Mark", "lastName","Myrie", "dob","17/07/1973", "address1","10 The Close", "address2","Southwark", "addressTown","LONDON", "addressPostcode","SE1 1BB"));

    List<Customer> signatoryList;

    @Test
    void setSignatoryList() {
        Customer customer1 = new mockCustomer1(testCustomerDetails);
        Customer customer2 = new mockCustomer1(testCustomerDetails2);
        Account account = new mockAccount(customer1);
        signatoryList = new ArrayList<>(){{add(customer1); add(customer2);}};
        NewAccountApplication application = new NewAccountApplication(customer1,account);
        application.setSignatoryList(signatoryList);
        assertEquals(signatoryList,application.getSignatoryList());
    }

//    @Test             // had to scrap this test for now, it won't work until the call to currentStage.close()
//                      // is moved out of the openAccount method (or it can only be tested while the javafx
//                      // thread is running).
//    void openAccountNoSignatories() {
//        Customer customer1 = new mockCustomer1(testCustomerDetails);
//        Account account = new mockAccount(customer1);
//        CustomerApplication customerApplication = new mockCustomerApplication(customer1);
//       // Stage stage = new mockStage();
//        NewAccountApplication application = new NewAccountApplication(customer1,account);
//        application.setCustomerApplication(customerApplication);
//       // application.setCurrentStage(new mockStage());
//        application.openAccount();
//        assertEquals("0",account.getSignatories());
//        assertEquals("135680",account.getAccountNumber());
//    }
}

class mockCustomer1 extends Customer {
    mockCustomer1(HashMap<String,String> inputMap){
        super(inputMap);
    }
}

class mockAccount extends Account {
    mockAccount(Customer customer){
        super(customer);
    }
    @Override
    public void writeData() {}

    @Override
    public Account getThisAccount() {
        setDetails(new HashMap<String,String>(){{put("accountNumber","135680");put("customerID",getCustomerID());
            put("currentBalance",getCurrentBalance()); put("signatories",getSignatories());}});
        return this;
    }
}

class mockStage extends Stage {
    @Override
    public void close() {
        // nothing
    }
}

class mockCustomerApplication extends CustomerApplication{
    mockCustomerApplication(Customer customer){
        super(customer);
    }

    @Override
    public void reload() {
        // nothing
    }
}