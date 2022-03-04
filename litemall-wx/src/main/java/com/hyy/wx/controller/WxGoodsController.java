package com.hyy.wx.controller;

import com.github.pagehelper.Page;
import com.hyy.core.config.SystemConfig;
import com.hyy.core.util.ResultUtil;
import com.hyy.db.domain.*;
import com.hyy.db.mapper.LitemallGoodsMapper;
import com.hyy.db.service.*;
import com.hyy.wx.annotation.LoginUser;
import com.sun.istack.internal.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.transform.Result;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

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

    @Autowired
    private LitemallCommentService litemallCommentService;

    @Autowired
    private LitemallProductService litemallProductService;

    @Autowired
    private LitemallCollectService litemallCollectService;

    private ExecutorService executorService= Executors.newFixedThreadPool(10);

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
            Integer id,
            Integer brandId,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size){
        Map<String,Object> goodsMap=new HashMap<>();
        if (id!=null){
            List<LitemallGoods> litemallGoodsList = litemallGoodsService.findGoodsById(id, page, size);
            LitemallCategory litemallCategory = litemallCategoryService.findById(id);
            goodsMap.put("litemallGoodsList",litemallGoodsList);
            goodsMap.put("litemallCategory",litemallCategory);
        }
        if (brandId!=null){
            List<LitemallGoods> litemallGoodsList = litemallGoodsService.findByBrandId(brandId, page, size);
            goodsMap.put("litemallGoodsList",litemallGoodsList);
        }



        return ResultUtil.ok(goodsMap);

    }

    /**
     * 通过物品id查询信息
     * @param id
     * @return
     */
    @RequestMapping("/detail")
    public Object detail(@LoginUser Integer userId, Integer id){
        Map<String,Object> entity=new HashMap<>();
        //商品信息
        Callable<LitemallGoods> litemallGoodsCallable=()->litemallGoodsService.findGoodById(id);

        //商品的属性信息
        Callable<List> litemallGoodsAttributeCallable=()-> litemallGoodsAttributeService.findByGoodsId(id);

        //评论
        Callable<List> litemallCommentCallable=()->litemallCommentService.findByLimit(id,1,3);

        //商品规格
        Callable<List> litemallGoodsSpecificationCallable= ()->litemallGoodsSpecificationService.findByGoodsId(id);

        //商品参数（价格，图片等）
        Callable<List> litemallGoodsProductCallable=()->litemallProductService.findAllById(id);




        FutureTask<LitemallGoods> litemallGoodsFuture=new FutureTask<>(litemallGoodsCallable);
        FutureTask<List> litemallGoodsAttributeFuture=new FutureTask<>(litemallGoodsAttributeCallable);
        FutureTask<List> litemallCommentFuture=new FutureTask<>(litemallCommentCallable);
        FutureTask<List> litemallGoodsSpecificationFuture=new FutureTask<>(litemallGoodsSpecificationCallable);
        FutureTask<List> litemallGoodsProductFuture=new FutureTask<>(litemallGoodsProductCallable);

        executorService.submit(litemallGoodsFuture);
        executorService.submit(litemallGoodsAttributeFuture);
        executorService.submit(litemallCommentFuture);
        executorService.submit(litemallGoodsSpecificationFuture);
        executorService.submit(litemallGoodsProductFuture);

        LitemallGoods litemallGoods=null;
        List<LitemallGoodsAttribute> litemallGoodsAttributeList=null;
        List<LitemallComment> litemallCommentList=null;
        List<LitemallGoodsSpecification> litemallGoodsSpecificationList=null;
        List<LitemallGoodsProduct> litemallGoodsProductList=null;
        try {
            litemallGoods = litemallGoodsFuture.get();
            litemallGoodsAttributeList=litemallGoodsAttributeFuture.get();
            litemallCommentList=litemallCommentFuture.get();
            litemallGoodsSpecificationList=litemallGoodsSpecificationFuture.get();
            litemallGoodsProductList=litemallGoodsProductFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        entity.put("litemallGoods",litemallGoods);
        entity.put("litemallGoodsAttributeList",litemallGoodsAttributeList);
        entity.put("litemallCommentList",litemallCommentList);
        entity.put("litemallGoodsSpecificationList",litemallGoodsSpecificationList);
        entity.put("litemallGoodsProductList",litemallGoodsProductList);

        return ResultUtil.ok(entity);

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

