package com.fhlzmy.web.test;


import com.alibaba.fastjson.JSONObject;
import com.fhlzmy.web.Enum.ResultStatus;
import com.fhlzmy.web.util.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/AdviceTest")
public class AdviceTestController {

    private static final HashMap<String, String> hashMap;

    static {
        hashMap = new HashMap<String, String>();
        hashMap.put("name", "啊啊啦啦啦");
        hashMap.put("value", "啊啊啦啦啦value");
    }

    /**
     * Advice的一个小测试类
     * @return
     */
    @GetMapping("/hello")
    public HashMap<String, String> hello(){


        return hashMap;
    }


    @GetMapping("/test")
    @ResponseBody
    public Result<Map<String, String>> test(){
        return Result.success(hashMap);
    }
}
