package com.hyy.wx.controller;

import com.hyy.core.util.ResultUtil;
import com.hyy.db.domain.LitemallCategory;
import com.hyy.db.service.LitemallCategoryService;
import com.sun.istack.internal.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wx/catalog")
@Validated
public class WxCategoryController {
    @Autowired
    private LitemallCategoryService litemallCategoryService;

    @RequestMapping("/index")
    public Object index(Integer id){
        if (id==null){
            id=1005000;
        }
        Map<String,Object> categoryMap=new HashMap();
        List<LitemallCategory> litemallCategoryList = litemallCategoryService.findAllNotFatherCategory();
        LitemallCategory litemallCategory = litemallCategoryService.findById(id);
        List<LitemallCategory> litemallCategoryL2List = litemallCategoryService.findInitByPid(id);

        categoryMap.put("litemallCategoryList",litemallCategoryList);
        categoryMap.put("litemallCategory",litemallCategory);
        categoryMap.put("litemallCategoryL2List",litemallCategoryL2List);

        return ResultUtil.ok(categoryMap);
    }

    @RequestMapping("/current")
    public Object current(@NotNull Integer id){
        Map<String,Object> categoryMap=new HashMap<>();
        LitemallCategory litemallCategoy = litemallCategoryService.findById(id);
        List<LitemallCategory> litemallCategoryL2List=null;
        if (litemallCategoy!=null){
            litemallCategoryL2List = litemallCategoryService.findInitByPid(id);

        }
        categoryMap.put("litemallCategoy",litemallCategoy);
        categoryMap.put("litemallCategoryL2List",litemallCategoryL2List);
        return ResultUtil.ok(categoryMap);
    }




}
