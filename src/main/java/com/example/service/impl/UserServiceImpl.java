package com.example.service.impl;

import com.example.config.exception.ValidationException;
import com.example.dao.UserDao;
import com.example.domain.UserDomain;
import com.example.service.UserService;
import com.example.service.converter.UserConverter;
import com.example.shared.UserDto;
import com.example.shared.ValidateGroups;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.validation.groups.Default;
import java.util.List;

@Service
public class UserServiceImpl extends MainService implements UserService {

    private UserDao userDao;

    private UserConverter userConverter;

    private PasswordEncoder passwordEncoder;

    private EntityManager entityManager;

    public UserServiceImpl(UserDao userDao, UserConverter userConverter, PasswordEncoder passwordEncoder, EntityManager entityManager) {
        this.userDao = userDao;
        this.userConverter = userConverter;
        this.passwordEncoder = passwordEncoder;
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public UserDto create(UserDto userDto) {
        validate(userDto, ValidateGroups.Create.class, Default.class);
        validateRoles(userDto);
        UserDomain userDomain = userConverter.dtoToEntity(userDto);
        userDomain.setPassword(passwordEncoder.encode(userDomain.getPassword()));
        if (userDomain.getActive() == null) {
            userDomain.setActive(true);
        }
        userDao.saveAndFlush(userDomain);
        entityManager.refresh(userDomain);
        return userConverter.entityToDto(userDomain);
    }

    @Transactional
    @Override
    public UserDto update(UserDto userDto) {
        validate(userDto, ValidateGroups.Create.class, Default.class);
        validateRoles(userDto);
        UserDomain userDomain = userConverter.dtoToEntity(userDto);
        userDomain.setPassword(passwordEncoder.encode(userDomain.getPassword()));
        UserDomain updatedUser = userDao.saveAndFlush(userDomain);
        return userConverter.entityToDto(updatedUser);
    }

    @Transactional
    @Override
    public UserDto findById(Long id) {
        UserDomain user = userDao.findOne(id);
        return userConverter.entityToDto(user);
    }

    @Transactional
    @Override
    public List<UserDto> findAll() {
        List<UserDomain> users = userDao.findAll();
        return userConverter.entityToDto(users);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }

    private void validateRoles(UserDto userDto) {
        boolean anyMatch = userDto.getRoles().stream().anyMatch(roleDto -> roleDto.getId() == null);
        if (anyMatch) {
            throw new ValidationException("Please fill all ids in roles!");
        }
    }
}
