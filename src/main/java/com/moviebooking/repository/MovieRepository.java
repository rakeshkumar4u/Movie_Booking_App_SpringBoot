package com.moviebooking.repository;

import com.moviebooking.entities.Movie;
import com.moviebooking.entities.MovieId;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, MovieId> {
	List<Movie> findByIdMovieNameContainingIgnoreCase(String movieName);
	@Query("SELECT SUM(t.numberOfTickets) FROM Ticket t WHERE t.movie.id = :movieId")
	Integer getTotalBookedTickets(@Param("movieId") MovieId movieId);
	
}

