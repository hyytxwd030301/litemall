package com.hyy.wx.controller;

import com.hyy.core.util.ResultUtil;
import com.hyy.db.mapper.LitemallOrderMapper;
import com.hyy.db.service.LitemallOrderService;
import com.hyy.wx.annotation.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/wx/order")
public class WxOrderController {

    @Autowired
    private LitemallOrderService litemallOrderService;

    @RequestMapping("/index")
    public Object index(@LoginUser Integer userId){
        if (userId==null){
            return ResultUtil.unlogin();
        }
        Map<String, Integer> orderInfo = litemallOrderService.orderInfo(userId);
        return ResultUtil.ok(orderInfo);
    }



}
