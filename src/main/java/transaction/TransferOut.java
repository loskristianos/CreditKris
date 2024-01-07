package transaction;

import account.Account;

import java.math.BigDecimal;

public class TransferOut extends Transaction{

    public TransferOut(Account account, String transactionAmount){
        super(account,transactionAmount);
        setTransactionType("Transfer Out");
    }
    @Override
    public String calculateNewBalance() {
        BigDecimal previousBalance = new BigDecimal(getPreviousBalance());
        BigDecimal transactionAmount = new BigDecimal(getTransactionAmount());
        return previousBalance.subtract(transactionAmount).toString();
    }
}
