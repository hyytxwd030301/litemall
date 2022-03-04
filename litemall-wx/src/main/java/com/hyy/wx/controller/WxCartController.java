package com.hyy.wx.controller;

import com.hyy.core.util.ResultUtil;
import com.hyy.db.domain.LitemallCart;
import com.hyy.db.service.LitemallCartService;
import com.hyy.wx.annotation.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/wx/cart")
public class WxCartController {

    @Autowired
    private LitemallCartService litemallCartService;

    @RequestMapping("/goodscount")
    public Object goodsCount(@LoginUser Integer userId){
        Map<String,Object> goodsCountMap=new HashMap<>();
        if (userId!=null){
            Integer count = litemallCartService.findCountByUserId(userId);
            if (count!=0){
                goodsCountMap.put("count",count);

            }
        }

        return ResultUtil.ok(goodsCountMap);
    }

}
