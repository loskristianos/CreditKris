package controller;

import customer.Customer;
import database.CustomerDataHandler;
import database.LoginDataHandler;
import interfaces.DataHandlerCreator;
import interfaces.DataObject;
import interfaces.DataObjectCreator;
import login.LoginObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ControllerTest {
    @Mock DataObjectCreator objectCreator;

    @Mock DataHandlerCreator dataHandlerCreator;
    @Mock
    LoginDataHandler loginDataHandlerMock;
    @Mock
    CustomerDataHandler customerDataHandlerMock;

    @InjectMocks Controller controllerMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void loginAttemptNoMatch() {
        Controller controller = controllerMock;
        LoginObject login = new LoginObject("user","password");
        List<DataObject> mockReturnList = new ArrayList<>();
        when(dataHandlerCreator.createLoginDataHandler(login)).thenReturn(loginDataHandlerMock);
        when(loginDataHandlerMock.getRecords()).thenReturn(mockReturnList);
        DataObject returnedObject = controller.loginAttempt(login);
        assertNull(returnedObject);
    }

    @Test
    void loginAttemptMatch() {
        Controller controller = controllerMock;
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
        when(dataHandlerCreator.createLoginDataHandler(login)).thenReturn(loginDataHandlerMock);
        when(loginDataHandlerMock.getRecords()).thenReturn(mockReturnList);
        when(dataHandlerCreator.createCustomerDataHandler(any())).thenReturn(customerDataHandlerMock);
        when(customerDataHandlerMock.getRecords()).thenReturn(mockCustomerList);
        DataObject returnedObject = controller.loginAttempt(login);
        assertEquals(customer.getDetails(),returnedObject.getDetails());
    }
}