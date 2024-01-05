package com.example.MoviesSystem.controllers;

import com.example.MoviesSystem.common.GlobalConstants;
import com.example.MoviesSystem.data.models.ApplicationUser;
import com.example.MoviesSystem.features.genres.services.contracts.GenreService;
import com.example.MoviesSystem.features.movies.models.ListMoviesViewModel;
import com.example.MoviesSystem.features.movies.models.MovieFormModel;
import com.example.MoviesSystem.features.movies.services.contracts.MovieService;
import com.example.MoviesSystem.features.users.services.contracts.UserService;
import com.example.MoviesSystem.security.SecurityConfig;
import com.example.MoviesSystem.security.SecurityUtil;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class MoviesController {
    private MovieService movieService;
    private GenreService genreService;
    private UserService userService;

    public MoviesController(MovieService movieService, GenreService genreService, UserService userService) {
        this.movieService = movieService;
        this.genreService = genreService;
        this.userService = userService;
    }

    @GetMapping("/movies")
    public String listMovies(@RequestParam Optional<String> search, Model model){
        ListMoviesViewModel movies = this.movieService.getAll(search.orElse(""));

        String currentUsername = SecurityUtil.getSessionUser();
        if(this.userService.IsExistByUsername(currentUsername)){
            String roleName = this.userService.getRoleNameByUsername(currentUsername);
            boolean isAdmin = roleName.equals(GlobalConstants.ADMIN_ROLE);
            model.addAttribute("isAdmin", isAdmin);
        }

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
    public String saveMovie(@Valid @ModelAttribute("movie") MovieFormModel movie, BindingResult bindingResult) throws Exception {

        if(bindingResult.hasErrors()){
            movie.genres = this.genreService.getAll();

            return  "create-movie";
        }

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
    public String update(@PathVariable("id") long id, @Valid @ModelAttribute("movie") MovieFormModel movie, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            movie.genres = this.genreService.getAll();

            return "update-movie";
        }

        this.movieService.update(id, movie);

        return "redirect:/movies";
    }

    @GetMapping("/movies/delete/{id}")
    public String delete(@PathVariable("id") long id) throws Exception {
        this.movieService.delete(id);

        return "redirect:/movies";
    }
}
