package com.example.service.impl;

import com.example.dao.RoleDao;
import com.example.domain.RoleDomain;
import com.example.service.RoleService;
import com.example.service.converter.RoleConverter;
import com.example.shared.RoleDto;
import com.example.shared.ValidateGroups;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class RoleServiceImpl extends MainService implements RoleService {

    private RoleConverter roleConverter;

    private RoleDao roleDao;

    public RoleServiceImpl(RoleConverter roleConverter, RoleDao roleDao) {
        this.roleConverter = roleConverter;
        this.roleDao = roleDao;
    }

    @Transactional
    @Override
    public RoleDto create(RoleDto roleDto) {
        validate(roleDto,ValidateGroups.Create.class);
        RoleDomain roleDomain = roleConverter.dtoToEntity(roleDto);
        RoleDomain savedRoleDomain = roleDao.save(roleDomain);
        return roleConverter.entityToDto(savedRoleDomain);
    }

    @Transactional
    @Override
    public RoleDto update(RoleDto roleDto) {
        validate(roleDto,ValidateGroups.Update.class);
        RoleDomain roleDomain = roleConverter.dtoToEntity(roleDto);
        RoleDomain updatedRoleDomain = roleDao.save(roleDomain);
        return roleConverter.entityToDto(updatedRoleDomain);
    }

    @Transactional
    @Override
    public RoleDto findById(Long id) {
        RoleDomain roleDomain = roleDao.findOne(id);
        return roleConverter.entityToDto(roleDomain);
    }

    @Transactional
    @Override
    public List<RoleDto> findAll() {
        List<RoleDomain> roleDomains = roleDao.findAll();
        return roleConverter.entityToDto(roleDomains);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        roleDao.delete(id);
    }
}
