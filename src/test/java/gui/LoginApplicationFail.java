package gui;

import customer.Customer;
import login.LoginObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginApplicationFail extends LoginApplication{
    @Override
    LoginObject createLoginObject(String username, String password) {
        return new MockLoginObject(username, password);
    }

    @Test
    void testLoginNoResult() {
        LoginApplication x = new LoginApplicationFail();
        try {assertEquals(-1,x.loginAttempt("user1","password123"));}
        catch (Exception ignored){}
    }
}

class MockLoginObject extends LoginObject {
    MockLoginObject(String username, String password){
        super(username,password);
    }
    public Customer loginAttempt(){
        return null;
    }
}