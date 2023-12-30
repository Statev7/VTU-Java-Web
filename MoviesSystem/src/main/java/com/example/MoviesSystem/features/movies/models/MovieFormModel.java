package com.example.MoviesSystem.features.movies.models;

import com.example.MoviesSystem.features.genres.models.GenreViewModel;
import com.example.MoviesSystem.features.movies.constants.ErrorMessages;
import jakarta.validation.constraints.NotEmpty;
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
    @NotEmpty(message = ErrorMessages.MOVIE_EMPTY_NAME)
    public String name;
    @NotEmpty(message = ErrorMessages.MOVIE_EMPTY_DESC)
    public String description;
    @NotEmpty(message = ErrorMessages.MOVIE_EMPTY_IMAGE)
    public String imageUrl;
    public long genreId;
    public List<GenreViewModel> genres;
}
