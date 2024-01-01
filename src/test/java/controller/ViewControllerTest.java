package controller;

import interfaces.DataObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ViewControllerTest {


    @Test
    void loginViewTest() {
        HashMap<String,String> loginData = new HashMap<>(){{put("username","test1");put("password","pass1");}};
        ViewController viewController = new ViewController();
        HashMap<String,String> returnedCustomerDetails = viewController.loginView(loginData);
        assertEquals(loginData,returnedCustomerDetails);
    }
}