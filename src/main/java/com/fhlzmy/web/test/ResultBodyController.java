package com.fhlzmy.web.test;


import com.fhlzmy.web.annotation.ResponseResultBody;
import com.fhlzmy.web.util.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/helloResultBody")
@ResponseResultBody
public class ResultBodyController {

    private static final HashMap<String, String> hashMap;

    static {
        hashMap = new HashMap<String, String>();
        hashMap.put("name", "啊啊啦啦啦");
        hashMap.put("value", "啊啊啦啦啦value");
    }

    @GetMapping("/hello")
    public HashMap<String, String> helloResult(){
        System.out.println("ResultBodyController.hello");
        return hashMap;
    }

    /** 测试重复包裹 */
    @GetMapping("/result")
    public Result<Map<String, String>> helloResult_1() {
        return Result.success(hashMap);
    }

    @GetMapping("/helloError")
    public HashMap<String, Object> helloError() throws Exception {
        throw new Exception("helloError");
    }

    /*@GetMapping("helloMyError")
    public HashMap<String, Object> helloMyError() throws Exception {
        throw new ResultException();
    }*/


}
