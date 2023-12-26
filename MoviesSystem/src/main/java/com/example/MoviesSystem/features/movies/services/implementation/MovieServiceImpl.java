package com.example.MoviesSystem.features.movies.services.implementation;

import com.example.MoviesSystem.data.repositories.MovieRepository;
import com.example.MoviesSystem.features.movies.models.MovieViewModel;
import com.example.MoviesSystem.features.movies.services.contracts.MovieService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {
    private MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<MovieViewModel> getAll() {
        return this.movieRepository
                .findAll()
                .stream()
                .sorted((m1, m2) -> m2.getCreatedOn().compareTo(m1.getCreatedOn()))
                .map((m) -> MovieViewModel
                        .builder()
                        .name(m.getName())
                        .description(m.getDescription())
                        .imageUrl(m.getImageUrl())
                        .genreName(m.getGenre().getName())
                        .build())
                .collect(Collectors.toList());
    }
}
