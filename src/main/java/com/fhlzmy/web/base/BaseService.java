package com.fhlzmy.web.base;


/**
 * 同BaseController一样, Service也应该需要一个BaseService .虽然不知道要写些什么方法,不管了。 先搞上去再说
 */
public class BaseService {


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
