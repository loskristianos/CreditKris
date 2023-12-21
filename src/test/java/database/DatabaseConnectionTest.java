/* Yes, I know this isn't a proper unit test if I'm writing to an actual database.
    I'll have another round of fighting with Mockito to get it to mock what I want
    when I've had some time to think of the right approach.
 */
package database;

import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import java.sql.Statement;

class DatabaseConnectionTest {

    @Test
    void testConnection() throws SQLException {
        var z = new DatabaseConnection().getDbConnection();
        Statement testStatement = z.createStatement();
        testStatement.executeUpdate("insert into login values(null,'joseph', 'pass023')");
        z.close();
    }
}