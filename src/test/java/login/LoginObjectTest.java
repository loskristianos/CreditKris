package login;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LoginObjectTest {

    String testName = "kris";
    String testPassword = "password1234";


    @Test
    void createObject() {
        var login = new LoginObject("kris", "password1234");
        assertEquals("kris" ,login.getUsername());
        assertEquals("password1234", login.getPassword());
    }

    @Test
    void objectSetAndGetMethods() {
        var login = new LoginObject(testName, testPassword);
        login.setAuthorised(true);
        login.setCustomerID("123");
        assertEquals(testName, login.getUsername());
        assertEquals(testPassword, login.getPassword());
        assertTrue(login.getAuthorised());
        assertEquals("123", login.getCustomerID());
    }
}