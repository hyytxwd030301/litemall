package com.hyy.wx.controller;

import com.hyy.core.util.ResultUtil;
import com.hyy.db.domain.LitemallCategory;
import com.hyy.db.domain.LitemallGoods;
import com.hyy.db.mapper.LitemallGoodsMapper;
import com.hyy.db.service.LitemallCategoryService;
import com.hyy.db.service.LitemallGoodsService;
import com.sun.istack.internal.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.transform.Result;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wx/goods")
public class WxGoodsController {
    @Autowired
    private LitemallGoodsService litemallGoodsService;

    @Autowired
    private LitemallCategoryService litemallCategoryService;

    @RequestMapping("/count")
    public Object count(){
        Integer count = litemallGoodsService.count();
        return ResultUtil.ok(count);
    }

    @RequestMapping("/category")
    public Object category(Integer id){
        Map<String,Object> categoryMap=new HashMap<>();
        LitemallCategory currentCategory = litemallCategoryService.findById(id);
        List<LitemallCategory> litemallCategoryList=null;
        LitemallCategory childCategory=null;
        if (currentCategory.getPid()==0){
           litemallCategoryList= litemallCategoryService.findInitByPid(currentCategory.getId());
           childCategory=litemallCategoryList.get(0);
        }else {
            childCategory=currentCategory;
            litemallCategoryList=litemallCategoryService.findInitByPid(currentCategory.getPid());
            currentCategory=litemallCategoryService.findById(currentCategory.getId());
        }

        categoryMap.put("currentCategory",currentCategory);
        categoryMap.put("childCategory",childCategory);
        categoryMap.put("bortherCategory",litemallCategoryList);
        return ResultUtil.ok(categoryMap);
    }

    /**
     * 获得固定分类下的goods信息
     * 上一级的信息
     * 厂商信息
     * @param id
     * @param page
     * @param size
     * @return
     */

    @RequestMapping("/list")
    public Object list(
            @NotNull Integer id,Integer page,Integer size){
        Map<String,Object> goodsMap=new HashMap<>();

        List<LitemallGoods> litemallGoodsList = litemallGoodsService.findGoodsById(id, page, size);
        LitemallCategory litemallCategory = litemallCategoryService.findById(id);
        goodsMap.put("litemallGoodsList",litemallGoodsList);
        goodsMap.put("litemallCategory",litemallCategory);
        return ResultUtil.ok(goodsMap);

    }
}

