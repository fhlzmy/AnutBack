package com.fhlzmy.web.Advice;


import com.fhlzmy.web.annotation.ResponseResultBody;
import com.fhlzmy.web.exception.BusException;
import com.fhlzmy.web.util.Result;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ResponseResultBodyAdvice implements ResponseBodyAdvice<Object> {


    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return AnnotatedElementUtils.hasAnnotation(methodParameter.getContainingClass(), ResponseResultBody.class) || methodParameter.hasMethodAnnotation(ResponseResultBody.class);
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if(o instanceof Result){
            return o;
        }
        return Result.success(o);
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Map<String, String> exceptionHandler(Exception exception){
        Map<String, String> map = new HashMap<String, String>();
        map.put("code", "501");
        map.put("message", "啊啊啦啦啦");
        return map;
    }

    @ResponseBody
    @ExceptionHandler(BusException.class)
    public Map<String, String> busExceptionhandler(BusException busException){
        Map<String, String> map = new HashMap<>();
        map.put("code", busException.getCode());
        map.put("message", busException.getMessage());
        return map;
    }

}
