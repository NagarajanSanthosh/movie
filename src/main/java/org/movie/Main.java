package org.movie;

import org.movie.view.ApplicationRunner;
import org.movie.view.Password;


public class Main {

    public static void main(String[] args) {
        Password pwd = new Password();
        System.out.println("Screen Master Welcomes you");
        ApplicationRunner applicationRunner = new ApplicationRunner();
        while (true) {
            if(pwd.displaySignUpLogin()) {
                System.out.println("Welcome " +pwd.getUserName());
                applicationRunner.displayMenu();
            }
        }


    }
}