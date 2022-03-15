package com.hyy.admin.controller;

import com.hyy.db.service.LitemallNoticeAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.Subject;

@RestController
@RequestMapping("admin/profile")
public class AdminProfileController {

    @Autowired
    private LitemallNoticeAdminService litemallNoticeAdminService;

    public Integer getAdminId(){
        Subject subject = new Subject();
        subject.getPrincipals()
    }
    @RequestMapping("/nonotice")
    public Object nonotice(){
        Integer count=litemallNoticeAdminService.countNoReadMessage();
    }
}
