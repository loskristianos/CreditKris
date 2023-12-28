package controller;

import customer.Customer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    @Test
    void newLoginAttempt() {
        new Controller().loginAttempt("kris", "password")
    }
}