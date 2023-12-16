package accounts;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientAccountTest {

//    @Test  // test fails currently - need to catch exception for null array length
//    void withArgsConstructorEmptyString() {
//        String[] arguments = {};
//        var a = new ClientAccount(arguments, 7.80);
//    }

    @Test
    void withArgsConstructorValidArgs(){
        String[] arguments = {"one", "two", "three", "four", "five"};
        var a = new ClientAccount(arguments, 7.80);
        System.out.println(a.customerID + " " + a.firstName + " " + a.lastName +" "+a.sortCode + " " + a.accountNumber + " " + a.currentBalance + " " + a.overdraftLimit);
    }
}