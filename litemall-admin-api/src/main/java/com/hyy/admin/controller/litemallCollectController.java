package com.hyy.admin.controller;

import com.hyy.admin.annotation.RequirePermissionDesc;
import com.hyy.core.util.ResultUtil;
import com.hyy.db.domain.LitemallCollect;
import com.hyy.db.service.LitemallCollectService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/admin/collect")
public class litemallCollectController {

    @Autowired
    private LitemallCollectService litemallCollectService;

    @RequiresAuthentication
    @RequiresPermissions("admin:collect:list")
    @RequirePermissionDesc(menu = {"用户管理","会员收藏"},operate = "查询")
    @RequestMapping("/list")
    public Object list(
            Integer userId,
            Integer valueId,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "add_time") String sort,
            @RequestParam(defaultValue = "desc") String order){
        List<LitemallCollect> litemallCollectList=null;


        litemallCollectList = litemallCollectService.findAll(userId,valueId,page,size,sort,order);

        return ResultUtil.ok(litemallCollectList);
    }
}
