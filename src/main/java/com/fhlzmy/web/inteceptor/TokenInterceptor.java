package com.fhlzmy.web.inteceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Service(value = "tokenInterceptor")
public class TokenInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("\n啊啊，  我是一个无情的TokenInterceptor.preHandle");

        HttpSession session = request.getSession();

        String token = request.getHeader("token");///从请求头获取token
        String uri = request.getRequestURI();

        System.out.println("uri:" + uri);
        System.out.println("。。。。");

        if(uri.contains("login.do")){ ///登录请求就不验证token了
            return true;
        }

        String sessionToken = (String) session.getAttribute("token");

        if(StringUtils.isEmpty(sessionToken)){ ///session中没有token， 送去登录
            request.getRequestDispatcher("/");
            return false;
        }else{


        }

        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
