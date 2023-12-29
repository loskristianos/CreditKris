package controller;

import interfaces.DataHandlerCreator;
import interfaces.DataObjectCreator;
import org.junit.jupiter.api.Test;
import transaction.DepositTransaction;
import transaction.Transaction;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    DataObjectCreator doc = new DataObjectCreator();
    DataHandlerCreator han = new DataHandlerCreator();
    HashMap<String, String> testDataHM = new HashMap<>(Map.of("transactionID","31826",  "accountNumber","909912", "transactionAmount","34.65", "transactionType","Deposit", "previousBalance","538.75", "newBalance","573.40", "transactionTime","2023/12/19 14:30:00", "authorised","Y", "additionalInfo",""));



    @Test
    void newRecordTest() {
        Transaction tx = new DepositTransaction(testDataHM);
        Controller x = new Controller(doc, han);
        x.newTransaction(tx);
    }

    @Test
    void newPendingRecords() {
        Transaction tx = new DepositTransaction(testDataHM);
        Controller y = new Controller(new DataObjectCreator(), new DataHandlerCreator());
        y.newPendingTransaction(tx);
    }
}