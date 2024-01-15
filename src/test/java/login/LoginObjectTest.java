package login;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class LoginObjectTest {

    String testName = "user1";
    String testPassword = "password1234";


    @Test
    void createObjectWithStrings() {
        LoginObject login = new LoginObject(testName, testPassword);
        assertEquals("user1" ,login.getUsername());
        assertEquals("password1234", login.getPassword());
    }

    @Test
    void createObjectWithHashMap() {
        HashMap<String,String> loginDetails = new HashMap<>(){{put("username",testName);put("password",testPassword);}};
        LoginObject login = new LoginObject(loginDetails);
        assertEquals("user1",login.getUsername());
        assertEquals("password1234",login.getPassword());
    }


    @Test
    void objectSetAndGetMethods() {
        var login = new LoginObject(testName, testPassword);
        login.setCustomerID("123");
        assertEquals("user1", login.getUsername());
        assertEquals("password1234", login.getPassword());
        assertEquals("123", login.getCustomerID());
    }
}