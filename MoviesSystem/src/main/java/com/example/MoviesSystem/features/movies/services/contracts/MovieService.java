package com.example.MoviesSystem.features.movies.services.contracts;

import com.example.MoviesSystem.features.movies.models.MovieFormModel;
import com.example.MoviesSystem.features.movies.models.MovieViewModel;

import java.util.List;

public interface MovieService {
    List<MovieViewModel> getAll();

    void create(MovieFormModel movie) throws Exception;

    void update(long id, MovieFormModel movie);

    void delete(long id) throws Exception;

    MovieFormModel findById(long id) throws Exception;
}
