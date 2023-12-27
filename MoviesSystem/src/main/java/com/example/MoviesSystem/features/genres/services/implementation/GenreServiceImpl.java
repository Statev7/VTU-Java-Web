package com.example.MoviesSystem.features.genres.services.implementation;

import com.example.MoviesSystem.data.repositories.GenreRepository;
import com.example.MoviesSystem.features.genres.models.GenreViewModel;
import com.example.MoviesSystem.features.genres.services.contracts.GenreService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreServiceImpl implements GenreService {

    private GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public List<GenreViewModel> getAll() {
        return this.genreRepository
                .findAll()
                .stream()
                .sorted((g1, g2) -> g1.getName().compareTo(g2.getName()))
                .map((g) -> GenreViewModel
                        .builder()
                        .id(g.getId())
                        .name(g.getName())
                        .build())
                .collect(Collectors.toList());
    }
}
