package org.movie.controller;

import org.movie.config.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class PasswordRepos {

    public void insertManager(String userName, String password, String securityAns) throws SQLException {
        String sql = "insert into managers (user_name, password, security-ans) values (?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setString(1, userName.trim().toLowerCase());
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, securityAns.toLowerCase().replace(" ", ""));
            preparedStatement.executeUpdate();
            System.out.println("Inserted successfully");
        }
    }

    public boolean userNameEligibility(String userName) throws SQLException{
        String sql = "select user_name from managers where user_name = ?";
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, userName);
            try(ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()) return true;
            }
        }

        return false;

    }
    public boolean passwordEligibility(String password) {
        int lowerCase = 0, upperCase = 0, numbers = 0, special = 0;
        if (password.length() < 8) return false;
        for (char a : password.toCharArray()) {
            if (Character.isUpperCase(a)) upperCase = 1;
            if (Character.isLowerCase(a)) lowerCase = 1;
            if (Character.isDigit(a)) numbers = 1;
            if (!Character.isDigit(a) && !Character.isLowerCase(a) && !Character.isUpperCase(a)) special = 1;
        }
        return (lowerCase == 1 && upperCase == 1 && numbers == 1 && special == 1);
    }

    public boolean checkManager(String userName, String password) throws SQLException {
        String sql = "select user_name, password from managers where user_name = ? and password = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, userName);
            statement.setString(2, password);
            try(ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) return true;
            }
        }
        return false;
    }
    public boolean securityQuestion(String userName, String securityAns) throws SQLException{
        String sql = "select security_ans from managers where user_name = ?";
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, userName);

            try(ResultSet resultSet = statement.executeQuery()) {
                if(resultSet.next()) {
                    String val = resultSet.getString("security_ans");
//                    System.out.println(val);
                    if (val != null) {
                        return val.equals(securityAns);
                    }

                }
            }
        }
        return false;
    }
    public boolean passwordRecovery(String userName, String password){
        String sql = "update managers set password = ? where user_name = ?";
        if(passwordEligibility(password)) {

            try (Connection connection = DBConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql);
            ) {
                statement.setString(1, password);
                statement.setString(2, userName);
                statement.executeUpdate();
                return true;
            } catch (SQLException e) {
                System.out.println("Error updating password");
            }
        }
        return false;
    }


}
