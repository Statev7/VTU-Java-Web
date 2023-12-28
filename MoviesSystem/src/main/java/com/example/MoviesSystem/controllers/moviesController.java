package com.example.MoviesSystem.controllers;

import com.example.MoviesSystem.data.models.Movie;
import com.example.MoviesSystem.features.genres.services.contracts.GenreService;
import com.example.MoviesSystem.features.movies.models.ListMoviesViewModel;
import com.example.MoviesSystem.features.movies.models.MovieFormModel;
import com.example.MoviesSystem.features.movies.models.MovieViewModel;
import com.example.MoviesSystem.features.movies.services.contracts.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class moviesController {
    private MovieService movieService;
    private GenreService genreService;

    public moviesController(MovieService movieService, GenreService genreService) {
        this.movieService = movieService;
        this.genreService = genreService;
    }

    @GetMapping("/movies")
    public String listMovies(@RequestParam Optional<String> search, Model model){
        ListMoviesViewModel movies = this.movieService.getAll(search.orElse(""));
        model.addAttribute("model", movies);

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
    public String saveMovie(@ModelAttribute("movie") MovieFormModel movie) throws Exception {
        this.movieService.create(movie);

        return "redirect:/movies";
    }

    @GetMapping("/movies/update/{id}")
    public String update(@PathVariable("id") long id, Model model) throws Exception {
        MovieFormModel movie = this.movieService.findById(id);
        movie.genres = this.genreService.getAll();

        model.addAttribute("id", id);
        model.addAttribute("movie", movie);

        return  "update-movie";
    }

    @PostMapping("/movies/update/{id}")
    public String update(@PathVariable("id") long id, @ModelAttribute("movie") MovieFormModel movie){
        this.movieService.update(id, movie);

        return "redirect:/movies";
    }

    @GetMapping("/movies/delete/{id}")
    public String delete(@PathVariable("id") long id) throws Exception {
        this.movieService.delete(id);

        return "redirect:/movies";
    }
}
