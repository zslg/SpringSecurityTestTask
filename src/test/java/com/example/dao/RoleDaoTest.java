package com.example.dao;

import com.example.DemoApplicationTests;
import com.example.domain.RoleDomain;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

public class RoleDaoTest extends DemoApplicationTests {

    @Autowired
    private RoleDao roleDao;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    @Transactional
    @Rollback(true)
    public void save(){

        RoleDomain roleDomain = new RoleDomain();
        roleDomain.setName("moderator");
        roleDao.saveAndFlush(roleDomain);

        RoleDomain one = roleDao.findOne(roleDomain.getId());

        Assert.assertEquals(roleDomain.getName(), one.getName());

    }

}