package database;

import interfaces.DataObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class AccountDataHandlerTest {

    @Mock
    Thread thread;
    @Mock
    Connection dbConnection;
//    @Mock
//    MockedStatic<DriverManager> DriverManager;
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
    void getRecords() {
    }

    @Test
    void update() {
    }
}