package com.fhlzmy.web.inteceptor;


import com.fhlzmy.web.Const.Const;
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

        String token = request.getHeader("X-Token");///从请求头获取token

        logger.info("token:"+token);

        String uri = request.getRequestURI();
        logger.info("uri:" + uri);
        /*if(uri.contains("login.do")){ ///登录请求就不验证token了
            logger.info("登录的请求...送你去登录");
            return true;
        }*/

        if(Const.getRequestWhiteUrl().contains(uri)){
            logger.info("");
        }

        String sessionToken = (String) session.getAttribute("token");

        logger.info("session中的token:" + sessionToken);
        if(StringUtils.isEmpty(sessionToken)){ ///session中没有token， 送去登录
            logger.info("没有登录,送你去登录");
            //request.getRequestDispatcher("localhost:8081/api/user/finaAllUser").forward(request, response);

            ///2020-07-08  我上面是🇳喝了假酒吧 ， 居然用request转发

            // 测试后台用,所以这里的下面两行代码就先注释掉哦. 后台代码测试好了再打开...
            //response.sendRedirect("http://127.0.0.1:9527#");///送你去登录
            //return false;
            return true;
        }else{

            if(sessionToken.equalsIgnoreCase(token)){ ///session中的token和request中的token是一致的 .. 那就是同一个用户 ，Go
                return true;
            }else{

                logger.error("session中的token不一致 ..  初步怀疑是session过期了 ..  没事 重登一下就好了 ");
                ////这里有问题
                return true;
            }
        }
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String uri = request.getRequestURI();
        if(uri.contains("logout.do")){ ///如果是注销登录的请求的话,重定向到登录页面去!
            logger.warn("👋,告辞!");
            response.sendRedirect("http://127.0.0.1:9527#");
        }
    }
}
