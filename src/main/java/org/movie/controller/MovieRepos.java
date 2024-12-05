package org.movie.controller;

import org.movie.config.DBConnection;
import org.movie.model.Movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieRepos {
    private void displayMovies(List<Movie> movies) {
        for (int i = 0; i < movies.size(); i++) {
            System.out.println(movies.get(i));
        }
    }
    protected void getMovies() throws SQLException {
        String sql = "select * from movies";
        List<Movie> movies = new ArrayList<>();
        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultset = statement.executeQuery();
        ) {
            while (resultset.next()) {
                Movie movie = new Movie(resultset.getShort("id"), resultset.getString("title"), resultset.getString("genre"),
                         resultset.getString("language"), resultset.getDouble("review"));

                movies.add(movie);
            }

        } catch (SQLException e) {
            System.out.println("Error fetching movies " + e.getMessage());
        }
        displayMovies(movies);

    }

}
