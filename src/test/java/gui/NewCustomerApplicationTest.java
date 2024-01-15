package gui;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class NewCustomerApplicationTest {

    HashMap<String,String> login = new HashMap<>(){{put("username","user1");put("password",null);}};
    HashMap<String,String> details = new HashMap<>(){{put("firstName","David");put("lastName","Brooks");}};

    @Test
    void verifyInputFields() {
        NewCustomerApplication x = new NewCustomerApplication();
        assertEquals(-1,x.verifyInputFields(login,details));    // blank field in login
        login.put("password","pass123");
        assertEquals(0,x.verifyInputFields(login,details));     // all fields in login
        details.put("address1",null);
        assertEquals(-1,x.verifyInputFields(login,details));    // blank field in details
        details.put("address1","some street");
        details.put("address2",null);
        assertEquals(0,x.verifyInputFields(login,details));     // blank field address2 in details

    }

    @Test
    void verifyPasswordMatch() {
        NewCustomerApplication x = new NewCustomerApplication();
        int z = x.verifyPasswordMatch("password1","password1");
        assertEquals(0,z);
    }

    @Test
    void verifyPasswordNoMatch() {
        NewCustomerApplication x = new NewCustomerApplication();
        int z = x.verifyPasswordMatch("password1","password2");
        assertEquals(-1,z);
    }
}