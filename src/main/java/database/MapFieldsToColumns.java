/*  Maps object fields to their corresponding database columns
*   Shouldn't really be hard-coded here, should pull the field and column names from
*   resource file(s) for ease of maintainability.
*/
package database;

import java.util.HashMap;
import java.util.Map;

public class MapFieldsToColumns {

    static  Map<String, String> account = new HashMap<>(Map.of("customerID","customer_id", "sortCode","sort_code", "accountNumber","account_number", "currentBalance","current_balance", "overdraftLimit","overdraft_limit", "signatories","signatories"));

    static Map<String, String> customer = new HashMap<>(Map.of("customerID","customer_id", "firstName","first_name","lastName","last_name", "dob","dob", "address1","address1", "address2","address2", "addressTown","town", "addressPostcode","postcode"));

    static Map<String, String> transaction = new HashMap<>(Map.of("transactionID","transaction_id", "accountNumber","account_number", "transactionAmount","transaction_amount", "transactionType","transaction_type", "previousBalance","previous_balance", "newBalance","new_balance", "transactionTime","transaction_time", "authorised","authorised", "additionalInfo","additional_info"));
}
