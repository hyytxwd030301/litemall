package com.hyy.wx.controller;

import com.hyy.core.util.ResultUtil;
import com.hyy.db.mapper.LitemallGoodsMapper;
import com.hyy.db.service.LitemallGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wx/goods")
public class WxGoodsController {
    @Autowired
    private LitemallGoodsService litemallGoodsService;

    @RequestMapping("/count")
    public Object count(){
        Integer count = litemallGoodsService.count();
        return ResultUtil.ok(count);
    }

}
