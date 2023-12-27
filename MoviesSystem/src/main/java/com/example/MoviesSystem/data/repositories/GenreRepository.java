package com.example.MoviesSystem.data.repositories;

import com.example.MoviesSystem.data.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {

}
