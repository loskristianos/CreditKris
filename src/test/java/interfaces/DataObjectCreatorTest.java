package interfaces;

import customer.Customer;
import login.LoginObject;
import org.junit.jupiter.api.Test;
import transaction.DepositTransaction;
import transaction.PendingTransaction;
import transaction.TransferTransaction;
import transaction.WithdrawalTransaction;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class DataObjectCreatorTest {

    @Test
    void createLoginObject() {
        String userTest = "username1";String passTest = "password1";
        DataObjectCreator objectCreator = new DataObjectCreator();
        DataObject returnedObject = objectCreator.createLoginObject(userTest,passTest);
        LoginObject expectedObject = new LoginObject("username1","password1");
        assertEquals(expectedObject.getObjectType(),returnedObject.getObjectType());
        assertEquals(expectedObject.getClass(),returnedObject.getClass());
        assertEquals(expectedObject.getDetails(),returnedObject.getDetails());
    }

    @Test
    void createPendingAuthorisation() {
        HashMap<String,String> inputMap = new HashMap<>(){{
            put("transactionID","1234");
            put("accountNumber","1078");
            put("customerID","56");
            put("transactionAmount", "309.46");
            put("transactionType", "Deposit");
        }};
        DataObjectCreator objectCreator = new DataObjectCreator();
        DataObject returnedObject = objectCreator.createPendingAuthorisation(inputMap);
        PendingTransaction expectedObject = new PendingTransaction();
        expectedObject.setDetails(inputMap);
        assertEquals(expectedObject.getObjectType(),returnedObject.getObjectType());
        assertEquals(expectedObject.getClass(),returnedObject.getClass());
        assertEquals(expectedObject.getDetails(), returnedObject.getDetails());
    }

    @Test
    void createNewDepositTransaction() {
        HashMap<String ,String > inputMap = new HashMap<>(){{
            put("transactionID","9919");
            put("accountNumber","4321");
            put("transactionAmount", "15.99");
            put("transactionType","Deposit");
            put("previousBalance","20.01");
            put("newBalance","36.00");
            }};
        DataObjectCreator objectCreator = new DataObjectCreator();
        DataObject returnedObject = objectCreator.createNewTransaction(inputMap);
        DepositTransaction expectedObject = new DepositTransaction();
        expectedObject.setDetails(inputMap);
        assertEquals(expectedObject.getObjectType(),returnedObject.getObjectType());
        assertEquals(expectedObject.getDetails(), returnedObject.getDetails());
    }

    @Test
    void createNewWithdrawalTransaction() {
        HashMap<String ,String > inputMap = new HashMap<>(){{
            put("transactionID","9919");
            put("accountNumber","4321");
            put("transactionAmount", "15.99");
            put("transactionType","Withdrawal");
            put("previousBalance","20.01");
            put("newBalance","36.00");
        }};
        DataObjectCreator objectCreator = new DataObjectCreator();
        DataObject returnedObject = objectCreator.createNewTransaction(inputMap);
        WithdrawalTransaction expectedObject = new WithdrawalTransaction();
        expectedObject.setDetails(inputMap);
        assertEquals(expectedObject.getObjectType(),returnedObject.getObjectType());
        assertEquals(expectedObject.getClass(),returnedObject.getClass());
        assertEquals(expectedObject.getDetails(), returnedObject.getDetails());
    }

    @Test
    void createNewTransferTransaction() {
        HashMap<String ,String > inputMap = new HashMap<>(){{
            put("transactionID","9919");
            put("accountNumber","4321");
            put("transactionAmount", "15.99");
            put("transactionType","Transfer");
            put("previousBalance","20.01");
            put("newBalance","36.00");
            put("additionalInfo","1234");
        }};
        DataObjectCreator objectCreator = new DataObjectCreator();
        DataObject returnedObject = objectCreator.createNewTransaction(inputMap);
        TransferTransaction expectedObject = new TransferTransaction();
        expectedObject.setDetails(inputMap);
        assertEquals(expectedObject.getObjectType(),returnedObject.getObjectType());
        assertEquals(expectedObject.getClass(),returnedObject.getClass());
        assertEquals(expectedObject.getDetails(), returnedObject.getDetails());
    }

    @Test
    void createNewCustomer() {
        HashMap<String ,String > inputMap = new HashMap<>(){{
            put("customerID","209");
            put("firstName","David");
            put("lastName","Brooks");
            put("dob","12/03/1945");
            put("address1","14 The Avenue");
            put("address2","The Village");
            put("addressTown","The Town");
            put("addressPostcode","AA1 2BB");
        }};
        DataObjectCreator objectCreator = new DataObjectCreator();
        DataObject returnedObject = objectCreator.createNewCustomer(inputMap);
        Customer expectedObject = new Customer();
        expectedObject.setDetails(inputMap);
        assertEquals(expectedObject.getObjectType(),returnedObject.getObjectType());
        assertEquals(expectedObject.getClass(),returnedObject.getClass());
        assertEquals(expectedObject.getDetails(), returnedObject.getDetails());
    }
}