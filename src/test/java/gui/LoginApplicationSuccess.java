package gui;

import customer.Customer;
import login.LoginObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginApplicationSuccess extends LoginApplication{

    @Override
    LoginObject createLoginObject(String username, String password) {
        return new MockLoginObject1(username, password);
    }

    @Override
    void launchCustomerApplication(Customer customer) throws Exception {
        // do nothing
    }

    @Test
    void testMatchingLogin() {
        LoginApplication x = new LoginApplicationSuccess();
        try {assertEquals(0,x.loginAttempt("user1","password123"));}
        catch (Exception ignored){}
    }
}

class MockLoginObject1 extends LoginObject {
    MockLoginObject1(String username, String password){
        super(username,password);
    }
    public Customer loginAttempt(){
        return new Customer(this);
    }
}
