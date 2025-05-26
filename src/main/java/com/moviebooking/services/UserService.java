package com.moviebooking.services;

import com.moviebooking.dto.UserDto;
import com.moviebooking.dto.UserUpdateDto;

public interface UserService {

    UserDto registerUser(UserDto userDto);

    UserDto login(String loginId, String password);

    void resetPassword(String loginId, String newPassword);

    void logout(String loginId);
    
    void updateEmailAndContact(UserUpdateDto dto);
    
    UserDto getUserProfile(String loginId);

}
