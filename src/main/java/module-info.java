module CreditKris {
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires java.sql;
    requires java.sql.rowset;

    exports gui;
    exports interfaces;
    exports account;
    exports customer;
    exports login;
    exports transaction;
}