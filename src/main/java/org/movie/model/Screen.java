package org.movie.model;

public record Screen(short id, short theatreId, String name,
                     String seatingLayout, int totalCapacity) {
}
