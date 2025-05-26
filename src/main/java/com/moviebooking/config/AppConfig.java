package com.moviebooking.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.moviebooking.dto.UserDto;
import com.moviebooking.entities.User;

@Configuration
public class AppConfig {

	@Bean
	public ModelMapper modelMapper() {
	    ModelMapper modelMapper = new ModelMapper();
	    modelMapper.typeMap(UserDto.class, User.class).addMappings(mapper -> 
	        mapper.skip(User::setId)
	    );
	    return modelMapper;
	}

}
