package com.moviebooking.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TicketDto {

    @NotBlank(message = "Login ID is required")
    private String loginId;

    @NotBlank(message = "Movie name is required")
    private String movieName;

    @NotBlank(message = "Theatre name is required")
    private String theatreName;

    @Min(value = 1, message = "At least 1 ticket must be booked")
    private int numberOfTickets;

    @NotBlank(message = "Seat numbers must be provided")
    private String seatNumbers;  // e.g., "A1,A2,A3"
    
}
