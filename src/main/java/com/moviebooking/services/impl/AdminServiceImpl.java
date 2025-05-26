package com.moviebooking.services.impl;

import com.moviebooking.entities.Movie;
import com.moviebooking.entities.MovieId;
import com.moviebooking.entities.Ticket;
import com.moviebooking.exceptions.ResourceNotFoundException;
import com.moviebooking.repository.MovieRepository;
import com.moviebooking.repository.TicketRepository;
import com.moviebooking.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final TicketRepository ticketRepository;
    private final MovieRepository movieRepository;

    @Override
    public String getBookedTicketCount(String movieName, String theatreName) {
        MovieId movieId = new MovieId(movieName, theatreName);
        int booked = ticketRepository.findByMovie_Id(movieId)
                .stream()
                .mapToInt(Ticket::getNumberOfTickets)
                .sum();

        return "Number of tickets booked for " + movieName + " at " + theatreName + " = " + booked + ".";
    }


    @Override
    public String updateTicketStatus(String movieName, String theatreName) {
        MovieId movieId = new MovieId(movieName, theatreName);
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found"));

        int booked = ticketRepository.findByMovie_Id(movieId)
                .stream()
                .mapToInt(Ticket::getNumberOfTickets)
                .sum();

        int available = movie.getTotalTickets();

        String status;
        if (available == 0) {
            status = "SOLD OUT";
        } else {
            status = "BOOK ASAP";
        }

        movie.setStatus(status);
        movieRepository.save(movie);

        return "Ticket available: " + available + ". " + status + " (Booked: " + booked + ")";
    }
    
    @Override
    public String deleteMovie(String movieName, String theatreName) {
        MovieId movieId = new MovieId(movieName, theatreName);
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found"));

        movieRepository.delete(movie);
        return "Movie " + movieName + " at " + theatreName + " deleted successfully."; // Modified line
    }

}
