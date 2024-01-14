package main;

import database.Setup;
import gui.LoginApplication;

public class Main {

    public static void main(String[] args){

        Setup.runChecks();
        new LoginApplication().displayScreen();
    }
}
