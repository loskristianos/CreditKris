package accounts;

public class ClientAccount extends Account {

    double overdraftLimit = 1500;

   public ClientAccount(String[] accountDetails, double currentBalance) {
       super(accountDetails, currentBalance);
   }
}


