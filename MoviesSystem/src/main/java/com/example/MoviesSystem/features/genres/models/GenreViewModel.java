package com.example.MoviesSystem.features.genres.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GenreViewModel {
    public long id;
    public String name;
}
