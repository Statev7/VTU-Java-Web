package com.example.MoviesSystem.data.repositories;

import com.example.MoviesSystem.data.constants.Queries;
import com.example.MoviesSystem.data.models.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query(Queries.SEARCH_QUERY)
    Page<Movie> search(@Param("search") String search, Pageable pageable);
}
