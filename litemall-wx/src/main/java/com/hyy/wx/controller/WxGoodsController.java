package com.hyy.wx.controller;

import com.github.pagehelper.Page;
import com.hyy.core.config.SystemConfig;
import com.hyy.core.util.ResultUtil;
import com.hyy.db.domain.*;
import com.hyy.db.mapper.LitemallGoodsMapper;
import com.hyy.db.service.*;
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

    @Autowired
    private LitemallGoodsAttributeService litemallGoodsAttributeService;

    @Autowired
    private LitemallIssueService litemallIssueService;

    @Autowired
    private LitemallGoodsSpecificationService litemallGoodsSpecificationService;

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

    /**
     * 通过物品id查询信息
     * @param id
     * @return
     */
    @RequestMapping("/detail")
    public Object detail(Integer id){
        Map<String,Object> goodsMap=new HashMap<>();
        LitemallGoods litemallGoods = litemallGoodsService.findGoodById(id);
        List<LitemallGoodsAttribute> litemallGoodsAttributeList = litemallGoodsAttributeService.findByGoodsId(id);
        List<LitemallIssue> litemallIssueList = litemallIssueService.findAll();
        List<LitemallGoodsSpecification> litemallGoodsSpecificationList = litemallGoodsSpecificationService.findByGoodsId(id);

        goodsMap.put("litemallGood",litemallGoods);
        goodsMap.put("litemallGoodsAttributeList",litemallGoodsAttributeList);
        goodsMap.put("litemallIssueList",litemallIssueList);
        goodsMap.put("litemallGoodsSpecificationList",litemallGoodsSpecificationList);

        return ResultUtil.ok(goodsMap);

    }


    /**
     * 通过id查找与当前商品相关物品
     * @param id
     * @return
     */
    @RequestMapping("/related")
    public Object related(Integer id){
        Map<String,Object> goodsMap=new HashMap<>();
        LitemallGoods litemallGood = litemallGoodsService.findGoodById(id);
        Page<LitemallGoods> litemallGoodsPage = (Page<LitemallGoods>) litemallGoodsService.findByCategoryId(litemallGood.getCategoryId(), 1, 6);
        goodsMap.put("litemallGoodsPage",litemallGoodsPage);
        return ResultUtil.ok(goodsMap);
    }
}

