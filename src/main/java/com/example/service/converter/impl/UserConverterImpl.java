package com.example.service.converter.impl;

import com.example.domain.RoleDomain;
import com.example.domain.UserDomain;
import com.example.service.converter.RoleConverter;
import com.example.service.converter.UserConverter;
import com.example.shared.RoleDto;
import com.example.shared.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserConverterImpl implements UserConverter {

    @Autowired
    private RoleConverter roleConverter;

    public UserDto entityToDto(UserDomain userDomain) {
        if (userDomain == null) {
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.setId(userDomain.getId());
        userDto.setName(userDomain.getName());
        userDto.setPassword(userDomain.getPassword());
        userDto.setActive(userDomain.getActive());
        List<RoleDomain> roles = userDomain.getRoles();
        if (roles != null && !roles.isEmpty()) {
            userDto.setRoles(roles.stream().map(roleConverter::entityToDto).collect(Collectors.toList()));
        }
        return userDto;
    }

    public UserDomain dtoToEntity(UserDto userDto) {
        if (userDto == null) {
            return null;
        }
        UserDomain userDomain = new UserDomain();
        userDomain.setId(userDto.getId());
        userDomain.setName(userDto.getName());
        userDomain.setPassword(userDto.getPassword());
        userDomain.setActive(userDto.getActive());
        List<RoleDto> roles = userDto.getRoles();
        if (roles != null && !roles.isEmpty()) {
            userDomain.setRoles(roles.stream().map(roleConverter::dtoToEntity).collect(Collectors.toList()));
        }
        return userDomain;
    }

    @Override
    public List<UserDto> entityToDto(List<UserDomain> userDomains) {
        if (userDomains == null) {
            return null;
        }
        return userDomains.stream().map(this::entityToDto).collect(Collectors.toList());
    }
}
