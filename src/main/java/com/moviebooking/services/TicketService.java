package com.moviebooking.services;

import java.util.List;

import com.moviebooking.dto.TicketDto;
import com.moviebooking.dto.TicketViewDto;


public interface TicketService {
    String bookTicket(TicketDto ticketDto);
    List<TicketViewDto> getAllBookingsByLoginId(String loginId);
}
