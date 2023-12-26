package com.example.MoviesSystem.controllers;

import com.example.MoviesSystem.features.movies.models.MovieViewModel;
import com.example.MoviesSystem.features.movies.services.contracts.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class moviesControllers {
    private MovieService movieService;

    public moviesControllers(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies")
    public String listMovies(Model model){
        List<MovieViewModel> movies = this.movieService.getAll();
        model.addAttribute("movies", movies);

        return "list-movies";
    }
}
