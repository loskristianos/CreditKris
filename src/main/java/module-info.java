module CreditKris {
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires java.sql;

    exports gui;
    exports interfaces;
    exports account;
    exports customer;
    exports login;
    exports transaction;
}