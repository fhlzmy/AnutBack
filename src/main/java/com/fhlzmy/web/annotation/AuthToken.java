package com.fhlzmy.web.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 这个注解注在方法上... 表示这个方法需要去校验一下Token,即验证当前用户是否已登录.
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthToken {

}
