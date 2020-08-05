package com.fhlzmy.web.Const;

/**
 *
 *
 * This is my MagicValue
 *
 * 这里用于存放魔术值,不允许修改,返回一些魔术值要全用这里定义的.
 *
 */
public class Const {

    private static String requestWhiteUrl;

    private static final Boolean success = Boolean.TRUE;

    private static final Boolean fail = Boolean.FALSE;

    private static final String man  = "1";

    private static final String women = "0";

    /**
     * 初始化请求白名单, 这里的白名单的request将会被Interceptor放行
     */
    static{
        Const.requestWhiteUrl = "login.do;register.do;";
    }






    public static String getRequestWhiteUrl(){
        return Const.requestWhiteUrl;
    }

}
