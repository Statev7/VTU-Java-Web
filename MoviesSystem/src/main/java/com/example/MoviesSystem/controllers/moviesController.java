package com.example.MoviesSystem.controllers;

import com.example.MoviesSystem.features.genres.services.contracts.GenreService;
import com.example.MoviesSystem.features.movies.models.MovieFormModel;
import com.example.MoviesSystem.features.movies.models.MovieViewModel;
import com.example.MoviesSystem.features.movies.services.contracts.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class moviesController {
    private MovieService movieService;
    private GenreService genreService;

    public moviesController(MovieService movieService, GenreService genreService) {
        this.movieService = movieService;
        this.genreService = genreService;
    }

    @GetMapping("/movies")
    public String listMovies(Model model){
        List<MovieViewModel> movies = this.movieService.getAll();
        model.addAttribute("movies", movies);

        return "list-movies";
    }

    @GetMapping("/movies/create")
    public String create(Model model){
        MovieFormModel movie = new MovieFormModel();
        movie.genres = this.genreService.getAll();

        model.addAttribute("movie", movie);

        return  "create-movie";
    }

    @PostMapping("/movies/create")
    public String saveClub(@ModelAttribute("movie") MovieFormModel movie) throws Exception {
        this.movieService.Create(movie);

        return "redirect:/movies";
    }
}
