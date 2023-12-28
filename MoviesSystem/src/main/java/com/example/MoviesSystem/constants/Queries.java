package com.example.MoviesSystem.constants;

public class Queries {
    public static final String SEARCH_QUERY = "select m from Movie m where lower(m.name) like lower(concat('%', :search, '%'))";
}
