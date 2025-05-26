package com.moviebooking.controller;

import com.moviebooking.dto.TicketDto;
import com.moviebooking.dto.TicketViewDto;
import com.moviebooking.services.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping("/book")
    public ResponseEntity<String> bookTicket(@Valid @RequestBody TicketDto ticketDto) {
        String response = ticketService.bookTicket(ticketDto);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/user-bookings/{loginId}")
    public ResponseEntity<List<TicketViewDto>> getUserBookings(@PathVariable String loginId) {
        List<TicketViewDto> bookings = ticketService.getAllBookingsByLoginId(loginId);
        return ResponseEntity.ok(bookings);
    }


}
