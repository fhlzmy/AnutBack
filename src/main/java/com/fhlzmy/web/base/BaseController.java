package com.fhlzmy.web.base;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 所有Controller的基类,所有的Controller都继承这个类,应该有所有controller都需要的东西
 * --->比如 log
 */
public class BaseController {

    protected Logger logger = LoggerFactory.getLogger(BaseController.class);




    /**
     * 判断是否为空
     * @param object
     * @return
     */
    public boolean isBlank(Object object){
        if(object == null){
            return false;
        }else{
            return true;
        }
    }


    /**
     * 判断是否不为空
     * @param object
     * @return
     */
    public boolean isNotBlank(Object object){
        return !isBlank(object);
    }


}
