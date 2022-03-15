package com.hyy.wx.controller;

import com.hyy.core.config.SystemConfig;
import com.hyy.core.config.SystemInitConfig;
import com.hyy.core.util.ResultUtil;
import com.hyy.db.domain.LitemallAddress;
import com.hyy.db.service.LitemallAddressService;
import com.hyy.wx.annotation.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wx/address")
public class WxAddressController {
    @Autowired
    private LitemallAddressService litemallAddressService;

    @RequestMapping("/list")
    public Object list(@LoginUser Integer userId){
        if (userId==null){
            return ResultUtil.unlogin();
        }
        List<LitemallAddress> litemallAddressList = litemallAddressService.findAllByPageAndSize(userId, 1, 20, SystemConfig.getLitemallMallAddress());
        return ResultUtil.ok(litemallAddressList);
    }

    @RequestMapping("/save")
    public Object save(@LoginUser Integer userId,@RequestBody LitemallAddress litemallAddress){
        if (userId==null){
            return ResultUtil.unlogin();
        }
        litemallAddress.setUserId(userId);
        litemallAddress.setDeleted(false);
        Boolean flag = litemallAddressService.add(litemallAddress);
        if (flag){
            return ResultUtil.ok();
        }
        return ResultUtil.fail();
    }

}
