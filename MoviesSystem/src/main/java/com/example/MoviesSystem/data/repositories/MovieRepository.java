package com.example.MoviesSystem.data.repositories;

import com.example.MoviesSystem.constants.Queries;
import com.example.MoviesSystem.data.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query(Queries.SEARCH_QUERY)
    List<Movie> search(@Param("search") String search);
}
