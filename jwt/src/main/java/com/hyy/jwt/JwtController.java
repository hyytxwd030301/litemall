package com.hyy.jwt;

import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

@RestController
@RequestMapping("/jwt/test")
public class JwtController {
    @RequestMapping("/test")
    public String test(String userName, HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("userName",userName);
        return "login ok";
    }

}
