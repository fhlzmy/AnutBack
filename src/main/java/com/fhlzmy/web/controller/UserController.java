package com.fhlzmy.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.fhlzmy.web.model.User;
import com.fhlzmy.web.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "/api/user/*")
public class UserController {

    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ResponseBody
    @RequestMapping(value = "findAllUsers")
    public String findAllUsers(){
        List<User> users = userService.findAll();
        return users.toString();
    }


    /**
     * 根据前台传过来的Token去获取User的数据
     * @return  我也不知道return些啥东西
     */
    @ResponseBody
    @RequestMapping(value = "info.do")
    public String getUserInfo(String token, HttpServletRequest request){
        HttpSession session = request.getSession();

        logger.info("我来获取用户信息啦~~~~");

        String[] roles = new String[]{"admin"};
        logger.debug("\n我这里先写死一些权限什么的,以后再来搞这个~");
        JSONObject obj = new JSONObject();
        obj.put("roles", roles);
        ///introduction: 'I am a super administrator',
        //    avatar: 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',
        //    name: 'Super Admin'

        obj.put("name", "Super Admin");
        obj.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        //obj.put("avatar", "http://online.sccnn.com/gif8/057/GIFCJ019.gif");
        obj.put("introduction","I am a super administrator");
        obj.put("code", 20000);
        obj.put("data", (User)session.getAttribute("user"));

        logger.debug("返回的数据: " + obj.toJSONString());
        return obj.toJSONString();
    }


    @ResponseBody
    @RequestMapping(value = "logout.do")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        session.removeAttribute("token");

        JSONObject obj = new JSONObject();
        obj.put("code", 50012);
        return obj.toJSONString();
    }


}
