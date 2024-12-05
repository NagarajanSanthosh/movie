package org.movie.view;

import org.movie.controller.PasswordRepos;

import java.util.Scanner;

public class Password {
    private static String userName;
    private static String password;
    private static String securityAns;
    private static final Scanner scanner = new Scanner(System.in);
    PasswordRepos passwordRepos;

    public Password() {
        passwordRepos = new PasswordRepos();
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public static void setSecurityAns(String securityAns) {
        Password.securityAns = securityAns;
    }

    public static String getSecurityAns() {
        return securityAns;
    }

    public boolean displaySignUpLogin() {
        System.out.println("Enter 1 to sign up");
        System.out.println("Enter 2 to login");
        boolean signUp = false;
        switch (scanner.nextInt()) {
            case 1 -> {
                if (signUp()) {
                    signUp = login();
                }
            }
            case 2 -> signUp = login();
            default -> System.out.println("Invalid Choice!!");

        }


        return signUp;
    }

    private boolean signUp() {

        try {
            System.out.println("Create an user name: ");
            this.setUserName(scanner.next());
            if (!passwordRepos.userNameEligibility(this.getUserName())) {
                System.out.println("Create an password: ");
                this.setPassword(scanner.next());
                if (passwordRepos.passwordEligibility(this.getPassword())) {
                    System.out.println("Please answer this SECURITY question in case of password recovery");
                    System.out.println("What's your favourite teacher's name?");
                    this.setSecurityAns(scanner.nextLine());
                    passwordRepos.insertManager(this.getUserName(), this.getPassword(), this.getSecurityAns());
                    System.out.println("Successfully signed up");
                    return true;
                } else {
                    System.out.println("Cannot signup password should be meet the following expectation\n1. At least 8 characters\n2. Contain lowercase\n3. Contain uppercase\n4. Contain Numbers\n5. Contain special characters `!@#$%^&*()_+|{}:;?/,.<>` ");
                }
            } else {
                System.out.println("User name already exists");
            }


        } catch (Exception e) {
            System.out.println("Error signing up");
        }
        return false;
    }

    private boolean login() {
        System.out.println("-----ScreenMaster Login-----");
        try {
            System.out.println("Enter username");
            this.setUserName(scanner.next().toLowerCase());
            System.out.println("Enter password");
            this.setPassword(scanner.next());
            if (passwordRepos.checkManager(this.getUserName(), this.getPassword())) {
                System.out.println("Login successful");
                return true;
            } else {
                System.out.println("Invalid Username / Password");

                System.out.println("Forgot Password ?");
                System.out.println("press `Y` to reset password `N` to cancel ");
                char passwordResponse = scanner.next().toLowerCase().charAt(0);
                switch (passwordResponse) {
                    case 'y' -> {
                        System.out.println("What's your favourite teacher's name?");
                        scanner.nextLine();
                        this.setSecurityAns(scanner.nextLine());
                        if (passwordRepos.securityQuestion(this.getUserName(), this.getSecurityAns())) {
                            System.out.println("Create new password");
                            this.setPassword(scanner.nextLine());
                            if (passwordRepos.passwordRecovery(this.getUserName(), this.getPassword())) {
                                System.out.println("Password created successfully");
                            }
                        }
                    }
                    case 'n' -> {

                    }
                }


            }
        } catch (Exception e) {
            System.err.print("Error fetching data");
        }
        return false;
    }


}
