package com.moviebooking.services;

public interface AdminService {

    String getBookedTicketCount(String movieName, String theatreName);

    String updateTicketStatus(String movieName, String theatreName);
    
    String deleteMovie(String movieName, String theatreName);

}
