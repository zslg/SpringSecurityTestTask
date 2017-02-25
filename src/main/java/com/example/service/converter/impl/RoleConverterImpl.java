package com.example.service.converter.impl;

import com.example.domain.RoleDomain;
import com.example.service.converter.RoleConverter;
import com.example.shared.RoleDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class RoleConverterImpl implements RoleConverter {
    public RoleDto entityToDto(RoleDomain roleDomain) {
        if (roleDomain == null) {
            return null;
        }
        RoleDto roleDto = new RoleDto();
        roleDto.setId(roleDomain.getId());
        roleDto.setName(roleDomain.getName());
        return roleDto;
    }

    public RoleDomain dtoToEntity(RoleDto roleDto) {
        if (roleDto == null) {
            return null;
        }
        RoleDomain roleDomain = new RoleDomain();
        roleDomain.setName(roleDto.getName());
        roleDomain.setId(roleDto.getId());
        return roleDomain;
    }

    @Override
    public List<RoleDto> entityToDto(List<RoleDomain> roleDomain) {
        return roleDomain.stream().map(this::entityToDto).collect(Collectors.toList());
    }
}
