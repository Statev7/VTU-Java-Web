package com.example.MoviesSystem.features.movies.models;

import lombok.Data;

import java.util.List;

@Data
public class ListMoviesViewModel {
    public String search;
    public List<MovieViewModel> movies;
}
