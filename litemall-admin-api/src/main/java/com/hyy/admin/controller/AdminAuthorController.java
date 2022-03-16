package com.hyy.admin.controller;

import com.hyy.admin.util.JacksonUtil;
import com.hyy.core.util.IpUtil;
import com.hyy.core.util.ResultUtil;
import com.hyy.db.domain.LitemallAd;
import com.hyy.db.domain.LitemallAdmin;
import com.hyy.db.service.LitemallAdminService;
import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("admin/author")
public class AdminAuthorController {
    @Autowired
    private LitemallAdminService litemallAdminService;

    @RequestMapping("/login")
    public Object login(@RequestBody String body, HttpServletRequest request) {
        Md5Hash md5Hash=new Md5Hash("admin123","123456",1024);
        String value = md5Hash.toHex();
        System.out.println(value);
        String userName= JacksonUtil.get(body,"username");
        String password = JacksonUtil.get(body, "password");
        Subject currentSubject = SecurityUtils.getSubject();

        try {
            currentSubject.login(new UsernamePasswordToken(userName,password));
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.fail();
        }

        currentSubject=SecurityUtils.getSubject();
        String ipAddr = IpUtil.getIpAddr(request);
        LitemallAdmin admin = (LitemallAdmin) currentSubject.getPrincipal();
        admin.setLastLoginIp(ipAddr);
        litemallAdminService.updateByUserId(admin);
        System.out.println("session:"+currentSubject.getSession().getId());

        return ResultUtil.ok();
    }
}
