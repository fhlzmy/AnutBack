package com.fhlzmy.web.services;


import com.fhlzmy.web.base.BaseService;
import com.fhlzmy.web.dao.UserRepository;
import com.fhlzmy.web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 这里应该是存放一些登录使用的一些方法
 */
@Service
public class LoginService extends BaseService {

    @Autowired
    private UserRepository userRepository;



    public User login(User loginUser){
        String account = loginUser.getAccount();

        User user = userRepository.findByAccount(account);

        if(isNotBlank(user)){
            ///不为空,去session


        }

        return user;
    }


}
