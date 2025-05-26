package com.moviebooking.controller;
import com.moviebooking.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    
    @GetMapping("/booked-count")
    public ResponseEntity<String> getBookedTickets(
            @RequestParam String movieName,
            @RequestParam String theatreName) {

        String message = adminService.getBookedTicketCount(movieName, theatreName);
        return ResponseEntity.ok(message);
    }

    // b. Update ticket status based on availability
    @PutMapping("/update-status")
    public ResponseEntity<String> updateTicketStatus(
            @RequestParam String movieName,
            @RequestParam String theatreName) {

        String status = adminService.updateTicketStatus(movieName, theatreName);
        return ResponseEntity.ok(status);
    }
    
    @DeleteMapping("/delete-movie")
    public ResponseEntity<String> deleteMovie(
            @RequestParam String movieName,
            @RequestParam String theatreName) {

        String result = adminService.deleteMovie(movieName, theatreName);
        return ResponseEntity.ok(result);
    }

}


