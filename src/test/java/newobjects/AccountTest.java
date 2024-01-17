package newobjects;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {



    @Test
    void signatoryCheck() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Transaction transaction = new Transaction();
        Account account = new Account(transaction);
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

}