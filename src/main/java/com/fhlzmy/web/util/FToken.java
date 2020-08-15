package com.fhlzmy.web.util;


import java.util.UUID;

public class FToken {


    public static String getToken(){
        return UUID.randomUUID().toString();
    }


}
