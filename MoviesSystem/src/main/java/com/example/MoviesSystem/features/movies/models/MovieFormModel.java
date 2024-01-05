package com.example.MoviesSystem.features.movies.models;

import com.example.MoviesSystem.features.genres.models.GenreViewModel;
import com.example.MoviesSystem.features.movies.constants.ErrorMessages;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieFormModel {
    @NotEmpty(message = ErrorMessages.MOVIE_EMPTY_NAME)
    private String name;
    @NotEmpty(message = ErrorMessages.MOVIE_EMPTY_DESC)
    private String description;
    @NotEmpty(message = ErrorMessages.MOVIE_EMPTY_IMAGE)
    private String imageUrl;
    private long genreId;
    public List<GenreViewModel> genres;
}
