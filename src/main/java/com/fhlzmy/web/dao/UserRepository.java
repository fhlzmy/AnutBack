package com.fhlzmy.web.dao;

import com.fhlzmy.web.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, String> {


    public List<User> findAll();

    public User findByAccount(String account);

    public User findByUserName(String userName);

}
