package org.movie.model;

public record Movie(short id, String title, String genre,
                    String language, double rating) {
}

