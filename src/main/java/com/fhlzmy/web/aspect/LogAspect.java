package com.fhlzmy.web.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class LogAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Pointcut("execution(* com.fhlzmy.web.controller.*.*(..))")
    public void log(){

    }

    @Before(value = "log()")
    public void before(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        String classMehod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        ResultLog resultLog = new ResultLog(url, ip, classMehod, args);

        logger.info("---------------before---------------");

        Map<String, String> para = getParaterMap(request);

        logger.info("请求的所有参数:" + para.toString());

        System.out.println(resultLog.toString());
    }


    @After("log()")
    public void after(){
        logger.info("---------------after---------------");
    }



    @AfterReturning(returning = "result", pointcut = "log()")
    public void after(Object result){
        logger.info("返回的东西：" + result.toString());
    }




    private class ResultLog{
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;

        public ResultLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "##########请求的参数{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    "'}##################'";
        }
    }


    public static Map<String, String> getParaterMap(HttpServletRequest request){
        Map<String, String> para = new HashMap<String, String>();
        if(request == null){
            return para;
        }

        Enumeration<String> parameterNames = request.getParameterNames();
        String k = "";
        String v = "";
        while (parameterNames.hasMoreElements()){
            k = parameterNames.nextElement();
            v = request.getParameter(k);
            para.put(k, v);
        }
        return para;
    }
}
