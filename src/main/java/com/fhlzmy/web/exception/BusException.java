package com.fhlzmy.web.exception;

/**
 * 一个提醒用的异常,继承RunTimeException，运行时异常。 到时候有问题统一用这个异常扔出提示 。 vue的前端来处理,弹个提示框。
 * 这样就不用很麻烦的在每个有问题的ajax处理。
 * Tips:配合ExceptionController使用
 */
public class BusException extends RuntimeException{

    private String code;

    private String message;

    private Boolean success;

    public BusException(){}

    public BusException(String code, String message, Boolean success){
        this.code = code;
        this.message = message;
        this.success = success;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
