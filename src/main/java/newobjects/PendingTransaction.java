package newobjects;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.math.BigDecimal;

@DatabaseTable (tableName = "pending_transactions")
public class PendingTransaction {

    @DatabaseField
    private String transactionID;
    @DatabaseField
    private Integer customerID;
    @DatabaseField
    private Integer accountNumber;
    @DatabaseField(dataType = DataType.BIG_DECIMAL)
    private BigDecimal transactionAmount;
    @DatabaseField(dataType = DataType.ENUM_NAME)
    private Transaction.Type transactionType;
    @DatabaseField
    private Integer signatoryID;
    @DatabaseField
    private Integer targetAccountNumber;
    @DatabaseField
    private String customerName;

    private PendingTransaction(Transaction transaction){
        setTransactionID(transaction.getTransactionID());
        setCustomerID(transaction.getCustomerID());
        setAccountNumber(transaction.getAccountNumber());
        setTransactionAmount(transaction.getTransactionAmount());
        setTransactionType(transaction.getTransactionType());
        setTargetAccountNumber(transaction.getTransferAccountNumber());

    }

    public PendingTransaction createPendingTransaction(Transaction transaction, Integer signatoryID){
        PendingTransaction pendingTransaction = new PendingTransaction(transaction);
        pendingTransaction.setSignatoryID(signatoryID);
        return pendingTransaction;
    }
    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public Transaction.Type getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(Transaction.Type transactionType) {
        this.transactionType = transactionType;
    }

    public Integer getSignatoryID() {
        return signatoryID;
    }

    public void setSignatoryID(Integer signatoryID) {
        this.signatoryID = signatoryID;
    }

    public Integer getTargetAccountNumber() {
        return targetAccountNumber;
    }

    public void setTargetAccountNumber(Integer targetAccountNumber) {
        this.targetAccountNumber = targetAccountNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
