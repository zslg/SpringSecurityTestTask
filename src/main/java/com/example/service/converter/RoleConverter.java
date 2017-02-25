package com.example.service.converter;

import com.example.domain.RoleDomain;
import com.example.shared.RoleDto;

import java.util.List;


public interface RoleConverter {

    RoleDto entityToDto(RoleDomain roleDomain);

    RoleDomain dtoToEntity(RoleDto roleDto);

    List<RoleDto> entityToDto(List<RoleDomain> roleDomain);

}
