package accounts;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommunityAccountTest {

    @Test
    void withArgsConstructorValidArgs() {
        String[] arguments = {"one", "two", "three", "four", "five"};
        var a = new CommunityAccount(arguments, 7.80);
        System.out.println(a.customerID + " " + a.firstName + " " + a.lastName +" "+a.sortCode + " " + a.accountNumber + " " + a.currentBalance + " " + a.overdraftLimit);
    }
}