package com.example.MoviesSystem.features.movies.services.implementation;

import com.example.MoviesSystem.data.models.Genre;
import com.example.MoviesSystem.data.models.Movie;
import com.example.MoviesSystem.data.repositories.GenreRepository;
import com.example.MoviesSystem.data.repositories.MovieRepository;
import com.example.MoviesSystem.features.movies.models.ListMoviesViewModel;
import com.example.MoviesSystem.features.movies.models.MovieFormModel;
import com.example.MoviesSystem.features.movies.models.MovieViewModel;
import com.example.MoviesSystem.features.movies.services.contracts.MovieService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {
    private final Integer ENTITIES_PER_PAGE = 3;

    private MovieRepository movieRepository;
    private GenreRepository genreRepository;

    public MovieServiceImpl(MovieRepository movieRepository, GenreRepository genreRepository) {
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
    }

    @Override
    public ListMoviesViewModel getAll(String search, Integer page) {

        long totalEntities = this.movieRepository.count();
        int totalPages = (int) Math.ceil((double) totalEntities / (double) ENTITIES_PER_PAGE) - 1;

        if(page < 0){
            page = 0;
        } else if (page > totalPages) {
            page = totalPages;
        }

        Page<Movie> movies = this.movieRepository.search(search, PageRequest.of(page, ENTITIES_PER_PAGE));

        var moviesList = movies
                .getContent()
                .stream()
                .sorted((m1, m2) -> m2.getCreatedOn().compareTo(m1.getCreatedOn()))
                    .map((m) -> MovieViewModel
                            .builder()
                            .id(m.getId())
                            .name(m.getName())
                            .description(m.getDescription())
                            .imageUrl(m.getImageUrl())
                            .genreName(m.getGenre().getName())
                            .build())
                    .collect(Collectors.toList());

        ListMoviesViewModel viewModel = new ListMoviesViewModel();
        viewModel.setSearch(search);
        viewModel.setMovies(moviesList);
        viewModel.setTotal(movies.getTotalPages());
        viewModel.setPageNumber(page);
        viewModel.setHasNext(movies.hasNext());
        viewModel.setHasPrevious(movies.hasPrevious());

        return viewModel;
    }

    @Override
    public void create(MovieFormModel movie) throws Exception {

        Genre genre = this.genreRepository.findById(movie.getGenreId()).get();

        if(genre == null) throw new Exception("Invalid genre");

       Movie movieToAdd = Movie.builder()
               .name(movie.getName())
               .description(movie.getDescription())
               .imageUrl(movie.getImageUrl())
               .genre(genre)
               .build();

       this.movieRepository.save(movieToAdd);
    }

    @Override
    public void update(long id, MovieFormModel movie) {
        Genre genre = this.genreRepository.findById(movie.getGenreId()).get();
        Movie movieToUpdate = this.movieRepository.findById(id).get();

        movieToUpdate.setName(movie.getName());
        movieToUpdate.setDescription(movie.getDescription());
        movieToUpdate.setImageUrl(movie.getImageUrl());
        movieToUpdate.setGenre(genre);

        this.movieRepository.save(movieToUpdate);
    }

    @Override
    public void delete(long id) throws Exception {
        Movie movieToDelete = this.movieRepository.findById(id).get();

        if(movieToDelete == null){
            throw new Exception("Invalid movie");
        }

        this.movieRepository.delete(movieToDelete);
    }

    @Override
    public MovieFormModel findById(long id) throws Exception {
        Movie movie = this.movieRepository.findById(id).get();

        if(movie == null){
            throw new Exception("Invalid movie");
        }

        MovieFormModel movieToReturn = MovieFormModel
                .builder()
                .name(movie.getName())
                .description(movie.getDescription())
                .imageUrl(movie.getImageUrl())
                .genreId(movie.getGenre().getId())
                .build();

        return movieToReturn;
    }
}
