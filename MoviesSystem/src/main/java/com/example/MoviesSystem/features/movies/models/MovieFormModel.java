package com.example.MoviesSystem.features.movies.models;

import com.example.MoviesSystem.features.genres.models.GenreViewModel;
import lombok.Data;

import java.util.List;

@Data
public class MovieFormModel {
    public String name;
    public String description;
    public String imageUrl;
    public long genreId;
    public List<GenreViewModel> genres;
}
