package com.example.MoviesSystem.features.movies.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MovieViewModel {
    private long id;
    private String name;
    private String description;
    private String imageUrl;
    private String genreName;
}
