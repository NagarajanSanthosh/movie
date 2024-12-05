package org.movie.model;

public record MovieSchedule(short id, short movieId, short screenId,
                            String showTime, double ticketPrice) {
}
