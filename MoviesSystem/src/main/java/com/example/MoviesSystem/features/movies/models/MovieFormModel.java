package com.example.MoviesSystem.features.movies.models;

import com.example.MoviesSystem.features.genres.models.GenreViewModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieFormModel {
    public String name;
    public String description;
    public String imageUrl;
    public long genreId;
    public List<GenreViewModel> genres;
}
