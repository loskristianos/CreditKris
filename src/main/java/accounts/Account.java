package accounts;

abstract class Account {

    String customerID;
    String firstName;
    String lastName;
    String sortCode;
    String accountNumber;
    double currentBalance;
    double overdraftLimit;
    int signatories;

    public Account(String[] accountDetails, double currentBalance) {
        // constructor - takes the array of details and the balance and sets appropriate account variables
        this.customerID = accountDetails[0];
        this.firstName = accountDetails[1];
        this.lastName = accountDetails[2];
        this.sortCode = accountDetails[3];
        this.accountNumber = accountDetails[4];
        this.currentBalance = currentBalance;
    }

    public void deposit() {
        // create new transaction object to add to balance
    }
    public void withdraw() {
        // create new transaction object to deduct from balance
    }

    public void transfer() {
        //create new transaction object to transfer from one account to another
    }
}
