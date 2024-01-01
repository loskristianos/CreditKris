package database;

import interfaces.DataObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.sql.*;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class AccountDataHandlerTest {

    @Mock
    Connection dbConnection;
    @Mock ResultSet mockResultSet;
    @Mock ResultSetMetaData mockMetaData;
    @Mock
    DataObject inputObject;
    @Mock
    Statement statement;

    @InjectMocks AccountDataHandler mockAccountDataHandler;

    private AutoCloseable x;
    AccountDataHandler accountDataHandler;

    @BeforeEach
    void setUp() {
        x = MockitoAnnotations.openMocks(this);
        this.accountDataHandler = mockAccountDataHandler;
    }

    @AfterEach
    void tearDown() throws Exception{
        x.close();
    }

    @Test
    void writeNewRecord() throws SQLException {
        HashMap<String,String> inputObjectDetails = new HashMap<>(){{
            put("accountNumber","1234");put("currentBalance","31.65");put("accountType","Client");
        }};
        when(inputObject.getDetails()).thenReturn(inputObjectDetails);
        try (MockedStatic<DriverManager> mockManager = Mockito.mockStatic(DriverManager.class)) {
            when(DriverManager.getConnection(anyString())).thenReturn(dbConnection);
            when(dbConnection.createStatement()).thenReturn(statement);
            accountDataHandler.writeNewRecord();
            verify(statement).executeUpdate("INSERT INTO accounts ('account_number','account_type','current_balance') VALUES ('1234','Client','31.65')");
        }
    }

    @Test
    void getRecords() throws SQLException {
        HashMap<String,String> customerDetails = new HashMap<>(){{
           put("customerID","123"); put("firstName","Rodney"); put("lastName","Price"); put("dob","12/09/1972");
           put("address1","1 The Street"); put("address2","Amble"); put("addressTown","MORPETH"); put("addressPostcode","NE65 1BK");
        }};
        when(inputObject.getObjectType()).thenReturn("Customer");
        when(inputObject.getDetails()).thenReturn(customerDetails);
        try (MockedStatic<DriverManager> mockManager = Mockito.mockStatic(DriverManager.class)) {
            when(DriverManager.getConnection(anyString())).thenReturn(dbConnection);
            when(dbConnection.createStatement()).thenReturn(statement);
            when(statement.executeQuery(anyString())).thenReturn(mockResultSet);
            when(mockResultSet.next()).thenReturn(true,false);
            when(mockResultSet.getMetaData()).thenReturn(mockMetaData);
                when(mockMetaData.getColumnCount()).thenReturn(2);
                when(mockMetaData.getColumnName(1)).thenReturn("account_number");
            when(mockResultSet.getString(1)).thenReturn("404616");
            when(mockMetaData.getColumnName(2)).thenReturn("account_type");
            when(mockResultSet.getString(2)).thenReturn("Client");
            List<DataObject> returnedObjects = accountDataHandler.getRecords();
            verify(statement).executeQuery("SELECT * FROM accounts WHERE account_number IN (SELECT account_number FROM accounts WHERE customer_id = 123 UNION SELECT account_number FROM signatories WHERE customer_id = 123)");
            assertEquals("Account", returnedObjects.getFirst().getObjectType());
            assertEquals("404616",returnedObjects.getFirst().getDetails().get("accountNumber"));
        }
    }

    @Test
    void update() {
    }
}