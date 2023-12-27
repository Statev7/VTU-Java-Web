package com.example.MoviesSystem.features.movies.services.implementation;

import com.example.MoviesSystem.data.models.Genre;
import com.example.MoviesSystem.data.models.Movie;
import com.example.MoviesSystem.data.repositories.GenreRepository;
import com.example.MoviesSystem.data.repositories.MovieRepository;
import com.example.MoviesSystem.features.movies.models.MovieFormModel;
import com.example.MoviesSystem.features.movies.models.MovieViewModel;
import com.example.MoviesSystem.features.movies.services.contracts.MovieService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {
    private MovieRepository movieRepository;
    private GenreRepository genreRepository;

    public MovieServiceImpl(MovieRepository movieRepository, GenreRepository genreRepository) {
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
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

    @Override
    public void Create(MovieFormModel movie) throws Exception {

        Genre genre = this.genreRepository.findById(movie.genreId).get();

        if(genre == null) throw new Exception("Invalid genre");

       Movie movieToAdd = Movie.builder()
               .name(movie.name)
               .description(movie.description)
               .imageUrl(movie.imageUrl)
               .genre(genre)
               .build();

       this.movieRepository.save(movieToAdd);
    }
}
