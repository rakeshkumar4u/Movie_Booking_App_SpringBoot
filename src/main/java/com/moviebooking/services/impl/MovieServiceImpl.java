package com.moviebooking.services.impl;

import com.moviebooking.entities.Movie;
import com.moviebooking.exceptions.ResourceNotFoundException;
import com.moviebooking.repository.MovieRepository;
import com.moviebooking.services.MovieService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public List<Movie> searchMoviesByName(String movieName) {
        List<Movie> movies = movieRepository.findByIdMovieNameContainingIgnoreCase(movieName);
        if (movies.isEmpty()) {
            throw new ResourceNotFoundException("No movies found with name: " + movieName);
        }
        return movies;
    }
}
