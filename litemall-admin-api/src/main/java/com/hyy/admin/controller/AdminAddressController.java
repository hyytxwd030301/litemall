package com.hyy.admin.controller;

import com.hyy.admin.annotation.RequirePermissionDesc;
import com.hyy.core.util.ResultUtil;
import com.hyy.db.domain.LitemallAddress;
import com.hyy.db.service.LitemallAddressService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/address")
public class AdminAddressController {
    @Autowired
    private LitemallAddressService litemallAddressService;

    @RequiresPermissions("admin:address:list")
    @RequirePermissionDesc(menu = {"用户管理","收货地址"},operate = "查询")
    @RequiresAuthentication
    @RequestMapping("/list")
    public Object list(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "add_time") String sort,
            @RequestParam(defaultValue = "desc") String order
    ){
        List<LitemallAddress> litemallAddressList=litemallAddressService.findAll(page,size,sort,order);
        return ResultUtil.ok(litemallAddressList);

    }

}
