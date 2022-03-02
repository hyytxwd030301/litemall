package com.hyy.wx.controller;

import com.hyy.core.config.SystemConfig;
import com.hyy.core.util.ResultUtil;
import com.hyy.db.domain.*;
import com.hyy.db.service.*;
import com.hyy.wx.annotation.LoginUser;
import jdk.nashorn.internal.codegen.CompilerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.image.ImageProducer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

@RestController
@Validated
@RequestMapping("/wx/home")
public class WxHomeController {

    @Autowired
    private LitemallAdService litemallAdService;

    @Autowired
    private LitemallCategoryService litemallCategoryService;

    @Autowired
    private LitemallCouponService litemallCouponService;

    @Autowired
    private LitemallGroupOnRuleService litemallGroupOnRuleService;

    @Autowired
    private LitemallBrandService litemallBrandService;

    @Autowired
    private LitemallGoodsService litemallGoodsService;

    @Autowired
    private LitemallTopicService litemallTopicService;

    private ExecutorService executorService= Executors.newFixedThreadPool(10);

    @RequestMapping("/index")
    public Object index(@LoginUser Integer userId){
        /**
         * 所要获得的数据
         * 1.轮播图的路径
         * 2.好的商品
         * 3.优惠劵
         * 4.品牌信息
         * 5.团购专区
         * 6.类目分类
         * 7.新品手法
         * 8.人气推荐
         */
        //获取callable
        Callable<List> litemallAdCallable=()->litemallAdService.index();
        Callable<List> litemallCategoryCallable=()->litemallCategoryService.findInit();
        Callable<List> litemallCouponCallable=null;
        if (userId==null){
            litemallCouponCallable=()->litemallCouponService.findInit(1,3);
        }else {
            litemallCouponCallable=()->litemallCouponService.findCouponWhenLogin(userId,1,3);
        }
        Callable<List> litemallGroupOnRulesCallable=()->litemallGroupOnRuleService.findInit(1,3);
        Callable<List> litemallBrandCallable=()->litemallBrandService.initFind(1, SystemConfig.getBrand());
        Callable<List> litemallGoodsCallable=()->litemallGoodsService.findInitIsNew(1,SystemConfig.getGoods());
        Callable<List> litemallGoodsIsHotCallable=()->litemallGoodsService.findInitIsHot(1,SystemConfig.getGoods());
        Callable<List> litemallTopicCallable=()->litemallTopicService.findInit(1,SystemConfig.getTopic());
        Callable<List> litemallCategoryGoodsCallable=this::getCategory;


        FutureTask<List> litemallFutureTask =new FutureTask<>(litemallAdCallable);
        FutureTask<List> litemallCategoryFuture=new FutureTask<>(litemallCategoryCallable);
        FutureTask<List> litemallCouponFuture=new FutureTask<>(litemallCouponCallable);
        FutureTask<List> litemallGroupOnRulesFurtrue = new FutureTask<>(litemallGroupOnRulesCallable);
        FutureTask<List> litemallBrandFuture=new FutureTask<>(litemallBrandCallable);
        FutureTask<List> litemallGoodsFuture=new FutureTask<>(litemallGoodsCallable);
        FutureTask<List> litemallGoodsIsHotFuture=new FutureTask<>(litemallGoodsIsHotCallable);
        FutureTask<List> litemallTopicFuture=new FutureTask<>(litemallTopicCallable);
        FutureTask<List> litemallCategoryGoodsFuture=new FutureTask<>(litemallCategoryGoodsCallable);


        executorService.submit(litemallFutureTask);
        executorService.submit(litemallCategoryFuture);
        executorService.submit(litemallCouponFuture);
        executorService.submit(litemallGroupOnRulesFurtrue);
        executorService.submit(litemallBrandFuture);
        executorService.submit(litemallGoodsFuture);
        executorService.submit(litemallGoodsIsHotFuture);
        executorService.submit(litemallTopicFuture);
        executorService.submit(litemallCategoryGoodsFuture);


        List<LitemallAd> litemallAdList = null;
        List<LitemallCategory> litemallCategoryList=null;
        List<LitemallCategory> litemallCouponList=null;
        List<LitemallGrouponRules> litemallGroupOnRulesList=null;
        List<LitemallBrand> litemallBrandList=null;
        List<LitemallGoods> litemallGoodsList=null;
        List<LitemallGoods> litemallGoodsIsHotList=null;
        List<LitemallTopic> litemallTopicList=null;
        List<Map> litemallCategoryGoodsList=null;


        try {
            litemallAdList = litemallFutureTask.get();
            litemallCategoryList = litemallCategoryFuture.get();
            litemallCouponList = litemallCouponFuture.get();
            litemallGroupOnRulesList = litemallGroupOnRulesFurtrue.get();
            litemallBrandList=litemallBrandFuture.get();
            litemallGoodsList=litemallGoodsFuture.get();
            litemallGoodsIsHotList=litemallGoodsIsHotFuture.get();
            litemallTopicList=litemallTopicFuture.get();
            litemallCategoryGoodsList=litemallCategoryGoodsFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }



        Map<String , Object> entity=new HashMap<>();
        entity.put("litemallAdList",litemallAdList);
        entity.put("litemallCategoryList",litemallCategoryList);
        entity.put("litemallCouponList",litemallCouponList);
        entity.put("litemallGroupOnRulesList",litemallGroupOnRulesList);
        entity.put("litemallBrandList",litemallBrandList);
        entity.put("litemallGoodsList",litemallGoodsList);
        entity.put("litemallGoodsIsHotList",litemallGoodsIsHotList);
        entity.put("litemallTopicList",litemallTopicList);
        entity.put("litemallCategoryGoodsList",litemallCategoryGoodsList);
//        System.out.println(litemallCategoryGoodsList);
        return ResultUtil.ok(entity);
    }
    public List<Map> getCategory(){
        List<Map> mapList=new ArrayList<>();
        List<LitemallCategory> litemallCategoryList = litemallCategoryService.findInitNotRecommend(1, SystemConfig.getCategoryGoods());
        for (LitemallCategory litemallCategory:litemallCategoryList){
            List<LitemallCategory> litemallCategoryList1 = litemallCategoryService.findInitByPid(litemallCategory.getId());
            List<Integer> idList=new ArrayList<>();
            for (LitemallCategory litemallCategory1:litemallCategoryList1){
                Integer id = litemallCategory1.getId();
                idList.add(id);
            }
            List<LitemallGoods> CategoryGoodsList = litemallGoodsService.findInitCategoryGoods(idList, 1, SystemConfig.getGoods());
            Map<String , Object> categoryMap=new HashMap<>();
            categoryMap.put("id",litemallCategory.getId());
            categoryMap.put("name",litemallCategory.getName());
            categoryMap.put("CategoryGoodsList",CategoryGoodsList);
            mapList.add(categoryMap);
        }
        return mapList;
    }
    /**
     * 商城信息
     * 项目名称，地址，电话号码，qq交流群
     */
    @RequestMapping("/about")
    public Object about(){
        Map<String,String> about=new HashMap<>();
        about.put("name",SystemConfig.getLitemallName());
        about.put("address",SystemConfig.getLitemallAddress());
        about.put("phoneNumber",SystemConfig.getLitemallPhoneNumber());
        about.put("qq",SystemConfig.getLitemallQQ());
        return ResultUtil.ok(about);
    }

}
