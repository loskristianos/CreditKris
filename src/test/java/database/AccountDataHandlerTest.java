package database;

import account.Account;
import account.ClientAccount;
import customer.Customer;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AccountDataHandlerTest {

    HashMap<String,String> accountData = new HashMap<>(Map.of("customerID","123", "accountNumber","12345678", "currentBalance","308.50", "overdraftLimit","1500", "signatories","1","accountType","client"));
    HashMap<String,String> customerData = new HashMap<>(Map.of("customerID","123", "firstName","Mark", "lastName","Myrie", "dob","17/07/1973", "address1","10 The Close", "address2","Southwark", "addressTown","LONDON", "addressPostcode","SE1 1BB"));
    HashMap<String, String> transactionData = new HashMap<>(Map.of("transactionID","1087",  "accountNumber","12345678", "transactionAmount","34.65", "transactionType","Deposit", "previousBalance","538.75", "newBalance","573.40", "transactionTime","2023/12/19 14:30:00", "authorised","Y", "additionalInfo",""));


    @Test
    void writeAccountData() {
       Account x = new ClientAccount(accountData);
       new AccountDataHandler(x).writeNewRecord();
    }




}