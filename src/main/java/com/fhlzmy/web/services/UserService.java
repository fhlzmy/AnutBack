package com.fhlzmy.web.services;

import com.fhlzmy.web.base.BaseService;
import com.fhlzmy.web.dao.UserRepository;
import com.fhlzmy.web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
public class UserService extends BaseService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        List<User> users = userRepository.findAll();

        return users;
    }


}
