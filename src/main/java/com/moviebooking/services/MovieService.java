package com.moviebooking.services;

import com.moviebooking.entities.Movie;

import java.util.List;

public interface MovieService {

    List<Movie> getAllMovies();

    List<Movie> searchMoviesByName(String movieName);
}
