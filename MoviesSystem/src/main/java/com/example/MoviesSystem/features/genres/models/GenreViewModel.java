package com.example.MoviesSystem.features.genres.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GenreViewModel {
    private long id;
    private String name;
}
