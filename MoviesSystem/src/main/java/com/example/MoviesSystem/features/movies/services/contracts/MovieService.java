package com.example.MoviesSystem.features.movies.services.contracts;

import com.example.MoviesSystem.features.movies.models.MovieViewModel;

import java.util.List;

public interface MovieService {
    List<MovieViewModel> getAll();
}
