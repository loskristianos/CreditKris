package login;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginObjectTest {

    String testName = "kris";
    String testPassword = "password1234";
    String testID = "90934";


    @Test
    void createObject() {
        var login = new LoginObject("kris", "password1234");
        assertEquals("kris" ,login.getUsername());
        assertEquals("password1234", login.getPassword());
    }

    @Test
    void objectSetAndGetMethods() {
        var login = new LoginObject(testName, testPassword);
        login.setCustomerID(testID);
        login.setAuthorised(true);
        assertEquals(testName, login.getUsername());
        assertEquals(testPassword, login.getPassword());
        assertEquals(testID, login.getCustomerID());
        assertTrue(login.getAuthorised());
    }
}