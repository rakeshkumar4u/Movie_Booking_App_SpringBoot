package com.moviebooking.repository;

import com.moviebooking.entities.MovieId;
import com.moviebooking.entities.Ticket;
import com.moviebooking.entities.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
	   List<Ticket> findByMovie_Id(MovieId movieId);
	   List<Ticket> findByUser(User user);
}

