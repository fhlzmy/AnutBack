package com.fhlzmy.web.exception;

/**
 * 一个提醒用的异常,继承RunTimeException，运行时异常。 到时候有问题统一用这个异常扔出提示 。 vue的前端来处理,弹个提示框。
 * 这样就不用很麻烦的在每个有问题的ajax处理。
 * Tips:配合ExceptionController使用
 */
public class BusException extends RuntimeException{


    private String message;

    private Boolean success;





}
