package login;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class LoginObjectTest {

    String testName = "kris";
    String testPassword = "password1234";


    @Test
    void createObjectWithStrings() {
        var login = new LoginObject("kris", "password1234");
        assertEquals("kris" ,login.getUsername());
        assertEquals("password1234", login.getPassword());
    }

    @Test
    void createObjectWithHashMap() {
        HashMap<String,String> loginDetails = new HashMap<>(){{put("username",testName);put("password",testPassword);}};
        LoginObject login = new LoginObject(loginDetails);
        assertEquals(testName,login.getUsername());
        assertEquals(testPassword,login.getPassword());
    }


    @Test
    void objectSetAndGetMethods() {
        var login = new LoginObject(testName, testPassword);
        login.setCustomerID("123");
        assertEquals(testName, login.getUsername());
        assertEquals(testPassword, login.getPassword());
        assertEquals("123", login.getCustomerID());
        assertEquals("Login",login.getObjectType());
    }
}