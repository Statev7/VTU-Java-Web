package com.example.MoviesSystem.features.genres.services.contracts;

import com.example.MoviesSystem.features.genres.models.GenreViewModel;

import java.util.List;

public interface GenreService {
    List<GenreViewModel> getAll();
}
