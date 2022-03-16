package com.hyy.admin.controller;

import com.google.common.annotations.VisibleForTesting;
import com.hyy.core.util.ResultUtil;
import com.hyy.db.domain.LitemallUser;
import com.hyy.db.service.LitemallUserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/user")
public class AdminUserController {
    @Autowired
    private LitemallUserService litemallUserService;

    @RequiresAuthentication
    @RequestMapping("/list")
    public Object userList(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            String mobile,
            @RequestParam(defaultValue = "add_time") String sort,
            @RequestParam("desc") String order){
        List<LitemallUser> litemallUserList = litemallUserService.findBySelective(page, size, mobile, sort, order);
        return ResultUtil.ok(litemallUserList);
    }

}
