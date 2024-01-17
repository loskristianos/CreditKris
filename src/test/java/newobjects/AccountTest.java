package newobjects;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {



    @Test
    void signatoryCheckTest() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Transaction transaction = new Transaction();
        Account account = new Account(Account.Type.CLIENT);
        account.setSignatories(1);
        Method testMethod = account.getClass().getDeclaredMethod("signatoryCheck", Transaction.class);
        testMethod.setAccessible(true);
        assertEquals(0, testMethod.invoke(account, transaction));
        assertEquals(Transaction.Status.NOT_SET, transaction.getTransactionStatus());
        account.setSignatories(2);
        Transaction transaction1 = new Transaction();
        assertEquals(-1,testMethod.invoke(account, transaction1));
        assertEquals(Transaction.Status.REQUIRES_AUTHORISATION, transaction1.getTransactionStatus());
    }

    @Test
    void balanceCheckTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Transaction transaction = new Transaction(Transaction.Type.WITHDRAWAL);
        transaction.setTransactionAmount(new BigDecimal("2000.00"));
        Account account = new Account(Account.Type.BUSINESS);
        account.setCurrentBalance(new BigDecimal("250.00"));
        Method testMethod = account.getClass().getDeclaredMethod("balanceCheck", Transaction.class);
        testMethod.setAccessible(true);
        // transaction fails balance check
        assertEquals(-1,testMethod.invoke(account, transaction));
        assertEquals(Transaction.Status.FAILED_BALANCE, transaction.getTransactionStatus());
        // transaction passes balance check
        Transaction transaction1 = new Transaction(Transaction.Type.WITHDRAWAL);
        transaction1.setTransactionAmount(new BigDecimal("100"));
        assertEquals(0,testMethod.invoke(account,transaction1));
        assertEquals(Transaction.Status.NOT_SET, transaction1.getTransactionStatus());
        // transaction doesn't require balance check
        Transaction transaction2 = new Transaction(Transaction.Type.DEPOSIT);
        transaction2.setTransactionAmount(new BigDecimal("5000.00"));
        assertEquals(0,testMethod.invoke(account,transaction2));
        assertEquals(Transaction.Status.NOT_SET, transaction2.getTransactionStatus());



    }

}