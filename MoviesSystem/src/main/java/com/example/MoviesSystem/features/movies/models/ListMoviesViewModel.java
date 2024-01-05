package com.example.MoviesSystem.features.movies.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Setter
@Getter
public class ListMoviesViewModel {
    private String search;
    private List<MovieViewModel> movies;
}
