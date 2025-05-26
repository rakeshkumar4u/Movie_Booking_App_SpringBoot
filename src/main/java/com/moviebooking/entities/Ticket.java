package com.moviebooking.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String seatNumbers;

    private int numberOfTickets;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "movie_name", referencedColumnName = "movieName"),
        @JoinColumn(name = "theatre_name", referencedColumnName = "theatreName")
    })
    private Movie movie;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}

