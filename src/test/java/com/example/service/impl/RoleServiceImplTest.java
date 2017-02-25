package com.example.service.impl;

import com.example.DemoApplicationTests;
import com.example.dao.RoleDao;
import com.example.domain.RoleDomain;
import com.example.service.RoleService;
import com.example.shared.RoleDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

public class RoleServiceImplTest extends DemoApplicationTests {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleDao roleDao;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Transactional
    @Rollback(value = false)
    @Test
    public void create() throws Exception {

        RoleDto roleDto = new RoleDto();
        roleDto.setName("test333");
        RoleDto roleDto1 = roleService.create(roleDto);

        RoleDomain one = roleDao.findOne(roleDto1.getId());

        org.junit.Assert.assertEquals(roleDto1.getName(), one.getName());

    }

    @Transactional
    @Rollback(value = true)
    @Test
    public void delete() throws Exception {

        RoleDomain roleDomain1 = roleDao.findAll().stream().filter(roleDomain -> "test333".equals(roleDomain.getName())).findFirst().get();

        roleService.delete(roleDomain1.getId());

        org.junit.Assert.assertTrue(!roleDao.findAll().stream().filter(roleDomain -> "test333".equals(roleDomain.getName())).findFirst().isPresent());

    }

}