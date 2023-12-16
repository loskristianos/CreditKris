package transactions;

abstract class Transaction {
    // variables - need to match columns in Transactions data table

    // constructor with args (need to match variables)
    public Transaction (String[] accountDetails, double transactionAmount, double currentBalance) {
        // set the variables from the arguments passed
    }

    // methods defined here
    abstract void amendBalance();   // void for now - I think this should set the balance and write it to the database
                                    // where the account object will pick up the new value rather than just return the
                                    // value from here as well. Will consider further.

    // send transaction to database from here? Separate method (will need amendBalance to return a value if so)

}
