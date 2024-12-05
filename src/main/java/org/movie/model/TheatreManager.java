package org.movie.model;

public record TheatreManager(int id, String name, String email, String password, String securityQuestion,
                             String securityAnswer) {
}
