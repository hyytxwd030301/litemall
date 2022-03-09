package com.hyy.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.hyy.entity.User;
import com.hyy.service.UserService;
import com.hyy.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/token")
    public Map<String,Object> token(User user){
        Boolean flag = userService.login(user.getUserName(), user.getPassword());
        Map<String,Object> objectMap=new HashMap<>();
        if (flag){
            Map<String, String> map=new HashMap<>();
            map.put("userName",user.getUserName());
            map.put("password", user.getPassword());
            String token = JwtUtil.getToken(map);
            objectMap.put("flag",true);
            objectMap.put("token",token);
            return objectMap;
        }
        objectMap.put("flag",false);
        objectMap.put("msg","无注册信息");
        return objectMap;


    }

    @RequestMapping("/login")
    public Object login(String token){
        Map<String ,Object> map=new HashMap<>();
        map.put("flag",true);
        map.put("msg","登录成功");
        return map;


    }


}
