package controller;

import customer.Customer;
import database.*;
import interfaces.*;
import account.*;
import login.LoginObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.invocation.MockitoMethod;
import org.mockito.listeners.MockitoListener;
import transaction.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ControllerTest {
    @Mock DataObjectCreator objectCreator;

    @Mock DataHandlerCreator dataHandlerCreator;
    @Mock DataHandler mockDataHandler;
    @Mock DataHandler mockDataHandler1;
    @InjectMocks Controller controllerMock;
    private AutoCloseable x;
    Controller controller;

    @BeforeEach
    void setUp() {
       x = MockitoAnnotations.openMocks(this);
       this.controller = controllerMock;
    }

    @AfterEach
    void tearDown() throws Exception {
        x.close();
    }

    @Test
    void loginAttemptNoMatch() {
        LoginObject login = new LoginObject("user","password");
        List<DataObject> mockReturnList = new ArrayList<>();
        when(dataHandlerCreator.createLoginDataHandler(login)).thenReturn(mockDataHandler);
        when(mockDataHandler.getRecords()).thenReturn(mockReturnList);
        DataObject returnedObject = controller.loginAttempt(login);
        assertNull(returnedObject);
    }

    @Test
    void loginAttemptMatch() {
        LoginObject login = new LoginObject("username","password");
        List<DataObject> mockReturnList = new ArrayList<>();
        LoginObject mockLoginReturn = new LoginObject("username","password");
        mockLoginReturn.setCustomerID("123456");
        mockReturnList.add(mockLoginReturn);
        Customer customer = new Customer();
        customer.setCustomerID("123456");
        HashMap <String,String> customerDetails = new HashMap<>(){{
            put("firstName","Barry");put("lastName","Wilson");}};
        customer.setDetails(customerDetails);
        List<DataObject> mockCustomerList = new ArrayList<>();
        mockCustomerList.add(customer);
        when(dataHandlerCreator.createLoginDataHandler(login)).thenReturn(mockDataHandler);
        when(mockDataHandler.getRecords()).thenReturn(mockReturnList);
        when(dataHandlerCreator.createCustomerDataHandler(any())).thenReturn(mockDataHandler1);
        when(mockDataHandler1.getRecords()).thenReturn(mockCustomerList);
        DataObject returnedObject = controller.loginAttempt(login);
        assertEquals(customer.getDetails(),returnedObject.getDetails());
    }

    @Test
    void getCustomerAccounts() {
        Customer customer = new Customer();
        Account account1 = new ClientAccount();
        Account account2 = new SmallBusinessAccount();
        List<DataObject> accountList = new ArrayList<>(){{
            add(account1); add(account2);
        }};
        when(dataHandlerCreator.createAccountDataHandler(any())).thenReturn(mockDataHandler);
        when(mockDataHandler.getRecords()).thenReturn(accountList);
        assertIterableEquals(accountList,controller.getCustomerAccounts(customer));
    }

    @Test
    void getAccountTransactions() {
        Account account = new ClientAccount();
        Transaction transaction1 = new DepositTransaction();
        Transaction transaction2 = new WithdrawalTransaction();
        List<DataObject> transactionList = new ArrayList<>(){{
            add(transaction1); add(transaction2);
        }};
        when(dataHandlerCreator.createTransactionDataHandler(any())).thenReturn(mockDataHandler);
        when(mockDataHandler.getRecords()).thenReturn(transactionList);
        assertIterableEquals(transactionList,controller.getAccountTransactions(account));
    }

    @Test
    void getPendingTransactionsForAccount() {
        Account account = new ClientAccount();
        PendingAuthorisation pending1 = new PendingAuthorisation();
        PendingAuthorisation pending2 = new PendingAuthorisation();
        List<DataObject> pendingList = new ArrayList<>(){{
            add(pending1); add(pending2);
        }};
        when(dataHandlerCreator.createAuthorisationDataHandler(account)).thenReturn(mockDataHandler);
        when(mockDataHandler.getRecords()).thenReturn(pendingList);
        assertIterableEquals(pendingList,controller.getPendingTransactionsForAccount(account));
    }

    @Test
    void getPendingTransactionsForCustomer() {
        Customer customer = new Customer();
        PendingAuthorisation pending1 = new PendingAuthorisation();
        PendingAuthorisation pending2 = new PendingAuthorisation();
        List<DataObject> pendingList = new ArrayList<>(){{
            add(pending1); add(pending2);
        }};
        when(dataHandlerCreator.createAuthorisationDataHandler(customer)).thenReturn(mockDataHandler);
        when(mockDataHandler.getRecords()).thenReturn(pendingList);
        assertIterableEquals(pendingList,controller.getPendingTransactionsForCustomer(customer));
    }

    @Test
    void newTransaction() {
        Transaction transaction1 = new DepositTransaction(); transaction1.setTransactionType("Deposit");
        Transaction transaction2 = new WithdrawalTransaction(); transaction2.setTransactionType("Withdrawal");
        Transaction transaction3 = new TransferTransaction(); transaction3.setTransactionType("Transfer");
        when(dataHandlerCreator.createTransactionDataHandler(any())).thenReturn(mockDataHandler);
        when(dataHandlerCreator.createAccountDataHandler(any())).thenReturn(mockDataHandler1);
            controller.newTransaction(transaction1);
            verify(mockDataHandler, times(1)).writeNewRecord();
            verify(mockDataHandler1, times(1)).update();
        controller.newTransaction(transaction2);
            verify(mockDataHandler,times(2)).writeNewRecord();
            verify(mockDataHandler1, times(2)).update();
        // to add transfer transactions...
    }
}