package com.moviebooking.services.impl;

import com.moviebooking.dto.UserDto;
import com.moviebooking.dto.UserUpdateDto;
import com.moviebooking.entities.User;
import com.moviebooking.exceptions.DuplicateResourceException;
import com.moviebooking.exceptions.ResourceNotFoundException;
import com.moviebooking.exceptions.ValidationException;
import com.moviebooking.repository.UserRepository;
import com.moviebooking.services.UserService;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    
    @PostConstruct
    public void init() {
        modelMapper.typeMap(UserDto.class, User.class).addMappings(mapper -> mapper.skip(User::setId));
    }

    @Override
    public UserDto registerUser(UserDto userDto) {
        if (userDto.getFirstName() == null || userDto.getLastName() == null ||
            userDto.getEmail() == null || userDto.getLoginId() == null ||
            userDto.getPassword() == null || userDto.getConfirmPassword() == null ||
            userDto.getContactNumber() == null) {
            throw new ValidationException("All fields are mandatory.");
        }

        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new DuplicateResourceException("Email already in use.");
        }
        if (userRepository.existsByLoginId(userDto.getLoginId())) {
            throw new DuplicateResourceException("Login ID already in use.");
        }

        if (!userDto.getPassword().equals(userDto.getConfirmPassword())) {
            throw new ValidationException("Password and Confirm Password must match.");
        }

        User user = modelMapper.map(userDto, User.class);
        user.setRole("USER");
        user.setLoggedIn(false);

        userRepository.save(user);

        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto login(String loginId, String password) {
        Optional<User> userOpt = userRepository.findByLoginId(loginId);
        if (userOpt.isPresent() && userOpt.get().getPassword().equals(password)) {
            User user = userOpt.get();
            user.setLoggedIn(true);
            userRepository.save(user);
            return modelMapper.map(user, UserDto.class);
        } else {
            throw new ValidationException("Invalid login ID or password.");
        }
    }

    @Override
    public void resetPassword(String loginId, String newPassword) {
        User user = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with login ID: " + loginId));

        user.setPassword(newPassword);
        userRepository.save(user);
    }

    @Override
    public void logout(String loginId) {
        User user = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with login ID: " + loginId));

        if (!user.isLoggedIn()) {
            throw new ValidationException("User is already logged out.");
        }
        user.setLoggedIn(false);
        userRepository.save(user);
    }
    
    @Override
    public void updateEmailAndContact(UserUpdateDto dto) {
        User user = userRepository.findByLoginId(dto.getLoginId())
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (!user.isLoggedIn()) {
            throw new ValidationException("User must be logged in to update details.");
        }

        user.setEmail(dto.getEmail());
        user.setContactNumber(dto.getContactNumber());

        userRepository.save(user);
    }
    
    @Override
    public UserDto getUserProfile(String loginId) {
        User user = userRepository.findByLoginId(loginId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return modelMapper.map(user, UserDto.class);
    }


}
