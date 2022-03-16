package com.hyy.admin.controller;

import com.hyy.admin.annotation.RequirePermissionDesc;
import com.hyy.core.util.ResultUtil;
import com.hyy.db.domain.LitemallFootprint;
import com.hyy.db.service.LitemallFootPrintService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/footprint")
public class AdminFootPrintController {
    @Autowired
    private LitemallFootPrintService litemallFootPrintService;

    @RequiresPermissions("admin:footprint:list")
    @RequirePermissionDesc(menu = {"用户管理","会员足迹"},operate = "查询")
    @RequiresAuthentication
    @RequestMapping("/list")
    public Object list(Integer userId, Integer goodsId,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer size,
                       @RequestParam(defaultValue = "add_time") String sort,
                       @RequestParam(defaultValue = "desc") String order){
       List<LitemallFootprint> litemallFootprintList = litemallFootPrintService.findAll(userId,goodsId,page,size,sort,order);
        return ResultUtil.ok(litemallFootprintList);

    }
}
