package com.example.dao;

import com.example.domain.UserDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserDao extends JpaRepository<UserDomain,Long> {

    UserDomain findByNameAndIsActiveTrue(String userName);

}
