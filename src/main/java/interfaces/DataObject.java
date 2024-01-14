/*
    This interface is really a remnant from an earlier design implementation which is largely not used
    anywhere anymore, with the MASSIVE EXCEPTION of the DAO superclass which still uses it in the construction
    of Insert statements for all the DataObject implementations (so the same method can produce insert
    statements for Customer, Account, Transaction, Signatory, PendingTransaction and Login objects). So until
    I can rewrite that into something better this needs to stay.
 */

package interfaces;

import java.util.HashMap;

public interface DataObject {
    HashMap<String, String> getDetails();
    void setDetails(HashMap<String,String> details);
}
