package database;

import interfaces.DataObject;
import login.LoginObject;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LoginDataHandlerTest {
    @Test
    void writeNewLoginDetailsNullID() {
        LoginObject b = new LoginObject("nullIdName1","nullIdPass1");
        DataHandler c = new LoginDataHandler(b);
        c.writeNewRecord();
    }

    @Test
    void returnMatchingRecord() {
        LoginObject d = new LoginObject("zdva3noi3z","r53qj8b1");
        DataHandler e = new LoginDataHandler(d);
        List<DataObject> de = e.getRecords();
        for (DataObject dataObject : de) {
            assertEquals("42", dataObject.getDetails().get("customerID"));
        }

    }
}