package org.movie.model;

import java.util.List;

public record Theatre(short id, String name, String location, List<Screen> screens) {
}

