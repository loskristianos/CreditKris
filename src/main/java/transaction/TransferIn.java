package transaction;

import account.Account;

import java.math.BigDecimal;

public class TransferIn extends Transaction{

    public TransferIn(Account account, String transactionAmount){
        super(account, transactionAmount);
        setTransactionType("Transfer In");
        super.writeData();
    }
    @Override
    public String calculateNewBalance() {
        BigDecimal previousBalance = new BigDecimal(getPreviousBalance());
        BigDecimal transactionAmount = new BigDecimal(getTransactionAmount());
        return previousBalance.add(transactionAmount).toString();
    }
}
