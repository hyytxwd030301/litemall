package com.hyy.wx.controller;

import com.github.pagehelper.Page;
import com.hyy.core.util.ResultUtil;
import com.hyy.db.domain.LitemallCollect;
import com.hyy.db.service.LitemallCollectService;
import com.hyy.wx.annotation.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wx/collect")
public class WxCollectController {

    @Autowired
    private LitemallCollectService litemallCollectService;

    @RequestMapping("/addOrDelete")
    public Object addOrDelete(@LoginUser Integer userId, LitemallCollect litemallCollect){
        Boolean flag=null;
        if (userId==null){
            return ResultUtil.unlogin();
        }
        litemallCollect.setUserId(userId);
        LitemallCollect collect = litemallCollectService.findByUserIdAndId(userId, litemallCollect.getValueId());
        if (collect==null){
            flag = litemallCollectService.add(litemallCollect);
        }else if (collect.getDeleted()){
            flag = litemallCollectService.Delete(collect, false);

        }else {
            flag = litemallCollectService.Delete(collect, false);
        }

        if (flag){
            return ResultUtil.ok();
        }
        return ResultUtil.fail();
    }

    @RequestMapping("/list")
    public Object list(@LoginUser Integer userId,Byte type,Integer page,Integer size){
        Map<String,Object> map=new HashMap<>();
        if (userId==null){
            return ResultUtil.unlogin();
        }
        LitemallCollect litemallCollect=new LitemallCollect();
        litemallCollect.setUserId(userId);
        litemallCollect.setType(type);
        Page<LitemallCollect> litemallCollectPage = (Page<LitemallCollect>) litemallCollectService.findByPageAndSize(litemallCollect, page, size);
        List<LitemallCollect> litemallCollectList = litemallCollectPage.getResult();
        Integer total = Math.toIntExact(litemallCollectPage.getTotal());
        Integer pageNum = litemallCollectPage.getPageNum();
        Integer pageSize = litemallCollectPage.getPageSize();
        map.put("litemallCollectList", litemallCollectList);
        map.put("total",total);
        map.put("pageNUm",pageNum);
        map.put("pageSize",pageSize);

        return ResultUtil.ok(map);
    }

}
