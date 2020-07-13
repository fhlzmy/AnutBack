package com.fhlzmy.web.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fhlzmy.web.base.BaseController;
import com.fhlzmy.web.dao.UserRepository;
import com.fhlzmy.web.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping(value = "/api/login/*")
public class LoginController extends BaseController {

    @Autowired
    private UserRepository userRepository;


    @ResponseBody
    @RequestMapping(value = "login.do")
    public String login(@RequestBody User loginUser, HttpServletRequest req){
        logger.info("\n这里是loginController的login方法......登录的user：" + loginUser.toString());

        User user = userRepository.findByAccount(loginUser.getAccount());

        HttpSession session = req.getSession();
        if(user != null){
            session.setAttribute("user", user);
            session.setAttribute("online", Boolean.TRUE);
        }else{

        }
        JSONObject obj = new JSONObject();
        obj.put("code", 20000);
        obj.put("data", user);
        obj.put("message", "登录成功!");
        obj.put("token", UUID.randomUUID().toString());

        System.out.println("登录的Token: " + obj.get("token"));
        session.setAttribute("token", obj.getString("token"));

        return obj.toJSONString();
    }

    @ResponseBody
    @RequestMapping(value = "loginOff.do")
    public String loginOff(@RequestBody User loginOffUser, HttpServletRequest req){
        HttpSession session = req.getSession();
        logger.info("\n这里是loginController的loginOff方法......将要退出的user：" + loginOffUser.toString());

        User user = userRepository.findByAccount(loginOffUser.getAccount());
        if(user != null){
            session.removeAttribute("user");
            session.setAttribute("online", Boolean.FALSE);
        }else{

        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 200);
        map.put("success", Boolean.TRUE);
        map.put("data", user);
        map.put("message", "登录成功!");
        map.put("token", UUID.randomUUID().toString());
        System.out.println("登录的Token: " + map.get("token"));
        JSONObject obj = JSONObject.parseObject(map.toString());
        return obj.toJSONString();

    }


}
