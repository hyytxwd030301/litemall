package com.hyy.admin.controller;

import com.hyy.admin.util.JacksonUtil;
import com.hyy.core.util.ResultUtil;
import com.hyy.db.domain.LitemallAdmin;
import com.hyy.db.service.LitemallAdminService;
import com.hyy.db.service.LitemallNoticeAdminService;
import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("admin/profile")
public class AdminProfileController {

    @Autowired
    private LitemallNoticeAdminService litemallNoticeAdminService;

    @Autowired
    private LitemallAdminService litemallAdminService;

    @RequestMapping("/nonotice")
    public Object nonotice(){
        Integer adminId = getAdminId();
        if (adminId==null){
            return ResultUtil.unlogin();
        }
        Integer count=litemallNoticeAdminService.countNoReadMessage(adminId);
        Map<String,Integer> countMap =new HashMap<>();
        countMap.put("count",count);
        return ResultUtil.ok(countMap);
    }

    public Integer getAdminId(){
        Subject currentSubject = SecurityUtils.getSubject();
        LitemallAdmin admin = (LitemallAdmin) currentSubject.getPrincipal();
        return admin.getId();
    }

    @RequiresAuthentication
    @RequestMapping("password")
    public Object password(@RequestBody String body){
        String newPassword = JacksonUtil.get(body, "newPassword");
        String newPassword2 = JacksonUtil.get(body, "newPassword2");
        String oldPassword = JacksonUtil.get(body, "oldPassword");
        if (newPassword!=null&&newPassword.equals(newPassword2)){
            Subject currentSubject = SecurityUtils.getSubject();
            LitemallAdmin admin = (LitemallAdmin) currentSubject.getPrincipal();
            admin.setPassword(newPassword);
            litemallAdminService.updatePassword(admin);
            return ResultUtil.ok();
        }

        return ResultUtil.fail();
    }
}
