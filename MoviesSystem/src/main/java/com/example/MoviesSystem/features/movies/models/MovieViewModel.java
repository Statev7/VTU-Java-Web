package com.example.MoviesSystem.features.movies.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MovieViewModel {
    public String name;
    public String description;
    public String imageUrl;
    public String genreName;
}
