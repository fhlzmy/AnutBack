package com.fhlzmy.web.services;

import com.fhlzmy.web.base.BaseService;
import com.fhlzmy.web.dao.UserRepository;
import com.fhlzmy.web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * 这是一个用户相关的service,用于处理和用户相关的东西...
 *
 *
 * 处理相关的逻辑
 *
 *开启事务
 */
@Service
@Transactional
public class UserService extends BaseService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        List<User> users = userRepository.findAll();

        return users;
    }


    /**
     * 用户注册,如果有重名的怎么办呢,返回啥东西呢???
     *
     * @param registerUser 即将注册的用户数据
     * @return User 用户数据...(具体是啥用户暂且先不管)
     */
    public User register(User registerUser){
        // 先获取用户名，去查询一下
        String account = registerUser.getAccount();
        String userName = registerUser.getUserName();
        User existUser = userRepository.findByAccount(account);
        User savedUser = new User();
        if(isBlank(existUser)){
            existUser = userRepository.findByUserName(userName);
            if(isBlank(existUser)){
                savedUser = userRepository.save(registerUser);
            }
        }

        if(isNotBlank(savedUser)){

        }
        return savedUser;
    }



}
