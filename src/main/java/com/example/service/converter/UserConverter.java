package com.example.service.converter;

import com.example.domain.UserDomain;
import com.example.shared.UserDto;

import java.util.List;


public interface UserConverter {

    UserDto entityToDto(UserDomain userDomain);

    UserDomain dtoToEntity(UserDto userDto);

    List<UserDto> entityToDto(List<UserDomain> userDomains);

}
