package database;

import interfaces.DataObject;
import login.LoginObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class LoginDataHandlerIntegrationTest {
    @BeforeAll
    static void beforeAll() {
        String url=null;
        String foreignKeysOff = "PRAGMA foreign_keys = OFF";
        String[] dropTables = {"login","customers","accounts","transactions","signatories","pending_authorisation"};
        String a = "CREATE TABLE login (customer_id INTEGER PRIMARY KEY NOT NULL ON CONFLICT IGNORE UNIQUE,  username TEXT UNIQUE NOT NULL, password TEXT NOT NULL)";
        String b = "CREATE TABLE customers (customer_id INTEGER PRIMARY KEY UNIQUE NOT NULL,first_name TEXT NOT NULL, last_name TEXT NOT NULL, dob TEXT NOT NULL, address1 TEXT NOT NULL, address2 TEXT, town TEXT, postcode TEXT NOT NULL)";
        String c = "CREATE TABLE accounts (account_number  INTEGER PRIMARY KEY NOT NULL UNIQUE,account_type TEXT NOT NULL, customer_id INTEGER REFERENCES customers (customer_id) NOT NULL,current_balance TEXT NOT NULL DEFAULT (0),overdraft_limit TEXT NOT NULL DEFAULT (0), signatories INTEGER NOT NULL DEFAULT (1))";
        String d = "CREATE TABLE transactions (transaction_id INTEGER PRIMARY KEY,account_number TEXT NOT NULL,transaction_amount TEXT NOT NULL,transaction_type TEXT NOT NULL,previous_balance TEXT NOT NULL, new_balance TEXT NOT NULL, transaction_time TEXT DEFAULT (CURRENT_TIMESTAMP) NOT NULL, authorised TEXT, additional_info TEXT)";
        String e = "CREATE TABLE signatories (account_number INTEGER NOT NULL,customer_id INTEGER NOT NULL)";
        String f = "CREATE TABLE pending_authorisation (transaction_id TEXT NOT NULL,account_number TEXT NOT NULL,customer_id TEXT NOT NULL,transaction_amount TEXT,transaction_type TEXT,additional_info TEXT)";
        String fkOn = "PRAGMA foreign_keys = ON";
        String[] databaseSetup = { a, b, c, d, e, f};

        Properties properties = new Properties();
        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));
            url = properties.getProperty("db.url");
        } catch (IOException z) {
            System.out.println(z.getMessage());
        }
        try (Connection dbConnection = DriverManager.getConnection(url);
             Statement statement = dbConnection.createStatement()) {
            for (int i=0;i < dropTables.length;i++) {
                statement.executeUpdate("DROP TABLE IF EXISTS "+dropTables[i]);
            }
            for (int i = 0; i < databaseSetup.length; i++) {
                statement.executeUpdate(databaseSetup[i]);
            }
        } catch (SQLException z) {
            System.out.println(z.getMessage());
        }
    }





    @Test
    void writeNewLoginDetailsNullID() {
        LoginObject b = new LoginObject("username1","Password99");
        DataHandler c = new LoginDataHandler(b);
        c.writeNewRecord();
    }

    @Test
    void returnMatchingRecord() {
        LoginObject d = new LoginObject("username1","password99");
        DataHandler e = new LoginDataHandler(d);
        List<DataObject> de = e.getRecords();
        for (DataObject dataObject : de) {
            assertEquals("1", dataObject.getDetails().get("customerID"));
        }

    }
}