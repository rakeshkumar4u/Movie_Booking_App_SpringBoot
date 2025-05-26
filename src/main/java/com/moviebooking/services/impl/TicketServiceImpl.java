package com.moviebooking.services.impl;

import com.moviebooking.dto.TicketDto;
import com.moviebooking.dto.TicketViewDto;
import com.moviebooking.entities.Movie;
import com.moviebooking.entities.MovieId;
import com.moviebooking.entities.Ticket;
import com.moviebooking.entities.User;
import com.moviebooking.exceptions.ResourceNotFoundException;
import com.moviebooking.repository.MovieRepository;
import com.moviebooking.repository.TicketRepository;
import com.moviebooking.repository.UserRepository;
import com.moviebooking.services.TicketService;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;
    @Override
    public String bookTicket(TicketDto ticketDto) {
       
        User user = userRepository.findByLoginId(ticketDto.getLoginId())
                .orElseThrow(() -> new ResourceNotFoundException("User with loginId " + ticketDto.getLoginId() + " not found"));

        
        MovieId movieId = new MovieId(ticketDto.getMovieName(), ticketDto.getTheatreName());
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found for booking."));

       
        if (movie.getTotalTickets() < ticketDto.getNumberOfTickets()) {
            throw new IllegalArgumentException("Not enough tickets available.");
        }

        
        movie.setTotalTickets(movie.getTotalTickets() - ticketDto.getNumberOfTickets());
        movieRepository.save(movie);

        // Save booking
        Ticket ticket = new Ticket();
        ticket.setMovie(movie);
        ticket.setNumberOfTickets(ticketDto.getNumberOfTickets());
        ticket.setSeatNumbers(ticketDto.getSeatNumbers());
        ticket.setUser(user);

        ticketRepository.save(ticket);

        return "Ticket booked successfully!\n\n" +
        "Booking Details:\n" +
        "User LoginId: " + user.getLoginId() + "\n" +
        "Movie: " + movie.getId().getMovieName() + "\n" +
        "Theatre: " + movie.getId().getTheatreName() + "\n" +
        "Tickets Booked: " + ticket.getNumberOfTickets() + "\n" +
        "Seat Numbers: " + ticket.getSeatNumbers();
    }
    @Override
    public List<TicketViewDto> getAllBookingsByLoginId(String loginId) {
        User user = userRepository.findByLoginId(loginId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        List<Ticket> tickets = ticketRepository.findByUser(user);

        return tickets.stream().map(ticket -> new TicketViewDto(
            user.getLoginId(),
            ticket.getMovie().getId().getMovieName(),
            ticket.getMovie().getId().getTheatreName(),
            ticket.getNumberOfTickets(),
            Arrays.asList(ticket.getSeatNumbers().split(","))
        )).collect(Collectors.toList());
    }

}
