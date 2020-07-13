package com.fhlzmy.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 显而易见,这是一个集中处理异常的控制器,我可能会扔出一些自己定义的异常. 我需要在前端显示自定义的异常信息,而不是springboot自带的乱七八糟
 * e.print();
 */
@Controller
public class ExceptionController extends AbstractErrorController {

    //不得已为之,没法继承BaseController了。  总不能让我impl BaseController然后自己实现logger吗.太蠢了
    private Logger logger = LoggerFactory.getLogger(ExceptionController.class);


    public ExceptionController(){
        super(new DefaultErrorAttributes());
    }



    @Override
    public String getErrorPath() {
        return null;
    }


    /**
     * 处理异常
     * @param request 请求
     * @param response 响应
     * @return
     */
    @RequestMapping("/error")
    public ModelAndView getErrorPath(HttpServletRequest request, HttpServletResponse response){

        logger.error("卧槽,我写的有问题了，目前是进入了ExceptionController....");
        Map<String, Object> mode = Collections.unmodifiableMap(getErrorAttributes(request, false));

        Throwable exception = getException(request);

        int status = (int) mode.get("status");
        String message = (String) mode.get("message");
        String errorMessage = getExceptionMessage(request);

        logger.info("Exception:" + exception.toString());
        if(!isJsonRequest(request)){ ///页面渲染
            ModelAndView view = new ModelAndView();
            view.addAllObjects(mode);
            view.addObject("errorMessage", errorMessage);
            view.addObject("status", status);
            view.addObject("exception", exception);
            return view;
        }else{ ///json请求
            Map<String, Object> error = new HashMap<>();

            error.put("success", false);
            error.put("errorMessage", errorMessage);
            error.put("message", message);

            return null;
        }
    }

    private String getExceptionMessage(HttpServletRequest request) {
        return  "系统出错，请联系管理员!";
    }


    /**
     * 用于捕获异常
     * @param request
     * @return
     */
    protected Throwable getException(HttpServletRequest request) {

        //获取异常
        Throwable exception = (Throwable) request.getAttribute("javax.servlet.error.exception");

        //SpringMVC可能会把异常包装成ServletException
        while (exception instanceof ServletException && exception.getCause() != null){
            exception = ((ServletException) exception).getCause();
        }
        return exception;
    }


    /**
     * 判断一下是不是页面上的ajax请求
     * @param request
     * @return
     */
    private boolean isJsonRequest(HttpServletRequest request) {
        boolean flag = false;
        String requestUrl = (String) request.getAttribute("javax.servlet.error.request_uri");

        if(!StringUtils.isEmpty(requestUrl) && requestUrl.endsWith(".json")){
            //request.getHeader("Accept").contains("application/json");  --->也可以判断请求头中的Accept是否是application/json

            flag = true;
        }

        return flag;
    }

}
