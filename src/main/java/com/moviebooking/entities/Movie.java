package com.moviebooking.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Movie {

    @EmbeddedId
    private MovieId id;

    private int totalTickets;
    
    @Column(nullable = false)
    private String status = "BOOK ASAP"; 
}
