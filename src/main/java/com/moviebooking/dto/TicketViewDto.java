package com.moviebooking.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketViewDto {
    private String loginId;
    private String movieName;
    private String theatreName;
    private int numberOfTickets;
    private List<String> seatNumbers;
}
