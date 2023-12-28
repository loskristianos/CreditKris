/*  Intermediate layer between UI views and database DataHandlers. Takes objects returned from the database (ArrayLists for the most part), and prepares them
    into a nice format for the UI classes to display, and vice versa. So it should work with any UI.

    This might end up not being necessary, the plan was (and still is) for the abstract DataHandler class to
    play that role, this is just a precaution in case the UI classes still end up getting
    database-specific stuff in them when I start writing them.
 */

package controller;

public class Controller {

    /*      Login:
            new HashMap<S,S>(enteredDetails) from ui fields
            create new loginObject(HashMap<>(enteredDetails) -> to LoginDataHandler

            receive LoginObject (List<DataObject> from DataHandler)
                -> send LoginObject to CustomerDataHandler
                receive Customer from DataHandler -> to ui

                -> send Customer to AccountDataHandler
                receive List<DataObject> Accounts -> to ui

                send Account to TransactionDataHandler
                receive List<DataObject> Transactions -> to ui
                send [Customer/Account] to AuthorisationDataHandler to get List<PendingAuthorisation> -> to ui


            Transaction (deposit, withdraw, transfer) request from ui
                signatories = 1
                    create Transaction  -> send to TransactionDataHandler
                                            -> send to AccountDataHandler to update balance
                signatories > 1
                    create Transaction (authorisation column = pending)
                        -> send to TransactionDataHandler (writeNew) (don't send to AccountHandler, unless we're going to show put funds on hold?)
                            -> send Account to signatories to get CustomerIDs
                        create PendingAuthorisation for each customerID
                            -> send List<PendingAuthorisation> to AuthorisationDataHandler (writeAll)

                confirmation of authorisation from ui
                    send PendingAuthorisation -> AuthorisationDataHandler (delete)
                    if last PendingAuthorisation for that transaction
                        -> send PendingAuthorisation -> TransactionDataHandler (update)
                        -> send Transaction -> AccountDataHandler (update)

               create new customer request from ui
                    Create LoginObject (username,password) -> to LoginDataHandler (writeNew)
                    receive LoginObject from LoginDataHandler with assigned CustomerID
                    create Customer -> to CustomerDataHandler (writeNew)

                open new account request from ui
                    Create Account object, populate from ui
                        send account to AccountDataHandler (writeNew)
                    signatories > 1
                      for each:
                        create Customer -> CustomerDataHandler to check for existing Customer
                            -> follow create new customer process (new login etc.)
                            -> AccountDataHandler to check for existing accounts
                            create signatory object - SignatoryDataHandler (writeNew)

     */

}
