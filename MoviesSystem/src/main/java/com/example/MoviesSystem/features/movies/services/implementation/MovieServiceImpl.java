package com.example.MoviesSystem.features.movies.services.implementation;

import com.example.MoviesSystem.data.models.Genre;
import com.example.MoviesSystem.data.models.Movie;
import com.example.MoviesSystem.data.repositories.GenreRepository;
import com.example.MoviesSystem.data.repositories.MovieRepository;
import com.example.MoviesSystem.features.movies.models.ListMoviesViewModel;
import com.example.MoviesSystem.features.movies.models.MovieFormModel;
import com.example.MoviesSystem.features.movies.models.MovieViewModel;
import com.example.MoviesSystem.features.movies.services.contracts.MovieService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    public ListMoviesViewModel getAll(String search) {

        List<Movie> movies = new ArrayList<>();

        if(search.isEmpty()){
            movies = this.movieRepository.findAll();
        }
        else{
            movies = this.movieRepository.search(search);
        }

        ListMoviesViewModel viewModel = new ListMoviesViewModel();

        viewModel.search = search;
        viewModel.movies = movies
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

        return viewModel;
    }

    @Override
    public void create(MovieFormModel movie) throws Exception {

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

    @Override
    public void update(long id, MovieFormModel movie) {
        Genre genre = this.genreRepository.findById(movie.getGenreId()).get();
        Movie movieToUpdate = this.movieRepository.findById(id).get();

        movieToUpdate.setName(movie.name);
        movieToUpdate.setDescription(movie.description);
        movieToUpdate.setImageUrl(movie.imageUrl);
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
