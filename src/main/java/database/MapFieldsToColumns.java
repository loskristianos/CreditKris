/*  Maps object fields to their corresponding database columns
*   Shouldn't really be hard-coded here, should pull the field and column names from
*   resource file(s) for ease of maintainability.
*/
package database;

import java.util.HashMap;

public class MapFieldsToColumns {

    public static HashMap<String,String> mappingsToDB = new HashMap<>() {{
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
        put("currentBalance", "current_balance");
        put("signatoryID", "signatory_id");
        put("targetAccountNumber", "target_account");
    }};

    public static HashMap<String,String> mappingsFromDB = new HashMap<>() {{
        put("customer_id","customerID");
        put("username", "username");
        put("password","password");
        put("first_name","firstName");
        put("last_name", "lastName");
        put("dob", "dob");
        put("address1", "address1");
        put("address2", "address2");
        put("town","addressTown");
        put("postcode","addressPostcode");
        put("account_number", "accountNumber");
        put("account_type", "accountType");
        put("overdraft_limit", "overdraftLimit");
        put("signatories", "signatories");
        put("transaction_id", "transactionID");
        put("transaction_amount", "transactionAmount");
        put("transaction_type", "transactionType");
        put("previous_balance", "previousBalance");
        put("new_balance", "newBalance");
        put("transaction_time", "transactionTime");
        put("authorised", "authorised");
        put("current_balance", "currentBalance");
        put("signatory_id","signatoryID");
        put("target_account","targetAccountNumber");
    }};
}
