package com.hyy.wx.controller;

import com.hyy.core.util.ResultUtil;
import com.hyy.db.domain.LitemallGroupon;
import com.hyy.db.service.LitemallGroupOnService;
import com.hyy.wx.annotation.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/wx/groupon")
public class WxGroupOnController {
    @Autowired
    private LitemallGroupOnService litemallGroupOnService;

    @RequestMapping("my")
    public Object my(@LoginUser Integer userId,short showType){
        if (userId==null){
            return ResultUtil.unlogin();
        }
        LitemallGroupon litemallGroupon=new LitemallGroupon();
        litemallGroupon.setUserId(userId);
        litemallGroupon.setStatus(showType);
        List<LitemallGroupon> litemallGrouponList = litemallGroupOnService.findAll(litemallGroupon, 1, 20);

        return litemallGrouponList;
    }
}
