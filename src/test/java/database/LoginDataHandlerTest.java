package database;

import login.LoginObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginDataHandlerTest {
    @Test
    void writeNewLoginDetailsNullID() {
        LoginObject b = new LoginObject("nullIdName","nullIdPass");
        DataHandler c = new LoginDataHandler(b);
        c.writeNewRecord();
    }
}