package com.example.service;

import com.example.shared.RoleDto;

import java.util.List;

public interface RoleService {

    RoleDto create(RoleDto roleDto);

    RoleDto update(RoleDto roleDto);

    RoleDto findById(Long id);

    List<RoleDto> findAll();

    void delete(Long id);

}
