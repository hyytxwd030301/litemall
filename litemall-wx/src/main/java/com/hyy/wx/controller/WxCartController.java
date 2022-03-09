package com.hyy.wx.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hyy.core.util.ResultUtil;
import com.hyy.db.domain.LitemallCart;
import com.hyy.db.domain.LitemallUser;
import com.hyy.db.service.LitemallCartService;
import com.hyy.wx.annotation.LoginUser;
import com.hyy.wx.util.WxJwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.transform.Result;
import java.util.HashMap;
import java.util.List;
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

    @RequestMapping("/add")
    public Object add(@LoginUser Integer userId,@RequestBody LitemallCart litemallCart){
        Boolean flag=null;
        Integer goodsId = litemallCart.getGoodsId();
        Short number = litemallCart.getNumber();
        Integer productId = litemallCart.getProductId();

        if (goodsId!=null&&number!=null&&productId!=null){
            if (userId==null){
                return ResultUtil.unlogin();
            }
           flag= (Boolean) litemallCartService.add(litemallCart,userId);
        }
        if (flag){
            return ResultUtil.ok();
        }

        return ResultUtil.fail();
    }

    /**
     * 购物车首页
     * 主要实现查询功能
     * @return
     */
    @RequestMapping("index")
    public Object index(){
        Map<String,Object> map=new HashMap<>();

        Page<LitemallCart> litemallCartPage = (Page<LitemallCart>) litemallCartService.findAll(1,20);
        List<LitemallCart> litemallCartList = litemallCartPage.getResult();

        map.put("litemallCartList",litemallCartList);


        return ResultUtil.ok(map);
    }

}
