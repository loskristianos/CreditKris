package database;

import account.Signatory;
import interfaces.DataObject;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

class SignatoryDataHandlerIntegrationTest {

    @Test
    void writeOneSignatory() {
        HashMap<String, String> x = new HashMap<>() {{
            put("customerID", "124562");
            put("accountNumber","234885");}};
        Signatory y = new Signatory(x);
        DataHandler xy = new SignatoryDataHandler(y);
        xy.writeNewRecord();
    }

    @Test
    void writeMultiples() {
        HashMap<String, String> x = new HashMap<>() {{
            put("customerID", "90926");
            put("accountNumber","4356");}};
        HashMap<String, String> y = new HashMap<>() {{
            put("customerID", "1324");
            put("accountNumber","22899");}};
        HashMap<String, String> z = new HashMap<>() {{
            put("customerID", "1627799");
            put("accountNumber","2348853333");}};
        Signatory a = new Signatory(x);
        Signatory b = new Signatory(y);
        Signatory c = new Signatory(z);
        ArrayList<DataObject> abc = new ArrayList<>();
        abc.add(a); abc.add(b); abc.add(c);
        SignatoryDataHandler xyz = new SignatoryDataHandler(abc);
        xyz.writeAllRecords();
    }
}