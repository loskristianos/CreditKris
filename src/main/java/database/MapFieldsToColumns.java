/*  Maps object fields to their corresponding database columns
*   Shouldn't really be hard-coded here, should pull the field and column names from
*   resource file(s) for ease of maintainability.
*/
package database;

import java.util.HashMap;

public class MapFieldsToColumns {

    static HashMap<String,String> mappings = new HashMap<>() {{
        put("customerID", "customer_id");
        put("username", "username");
        put("password","password");
        put("firstName", "first_name");
        put("lastName", "last_name");
        put("dob", "dob");
        put("address1", "address1");
        put("address2", "address2");
        put("addressTown", "town");
        put("addressPostcode", "postcode");
        put("accountNumber", "account_number");
        put("accountType", "account_type");
        put("overdraftLimit", "overdraft_limit");
        put("signatories", "signatories");
        put("transactionID", "transaction_id");
        put("transactionAmount", "transaction_amount");
        put("transactionType", "transaction_type");
        put("previousBalance", "previous_balance");
        put("newBalance", "new_balance");
        put("transactionTime", "transaction_time");
        put("authorised", "authorised");
        put("additionalInfo", "additional_info");
        put("currentBalance", "current_balance");
    }};


}
