package com.hyy.db.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hyy.db.domain.*;
import com.hyy.db.mapper.LitemallCartMapper;
import com.hyy.db.service.LitemallCartService;
import com.hyy.db.service.LitemallGoodsService;
import com.hyy.db.service.LitemallProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LitemallCartServiceImpl implements LitemallCartService {

    @Autowired
    private LitemallCartMapper litemallCartMapper;

    @Autowired
    private LitemallProductService litemallProductService;

    @Autowired
    private LitemallGoodsService litemallGoodsService;

    @Override
    public Integer findCountByUserId(Integer userId) {
        LitemallCartExample litemallCartExample=new LitemallCartExample();
        litemallCartExample.or().andDeletedEqualTo(false).andUserIdEqualTo(userId);
        Page<LitemallCart> litemallCartPage= (Page<LitemallCart>) litemallCartMapper.selectByExample(litemallCartExample);
        Integer total = Math.toIntExact(litemallCartPage.getTotal());
        return total;
    }

    @Override
    public LitemallCart findByGoodIdAndProductId(Integer goodId, Integer productId) {
        LitemallCartExample litemallCartExample=new LitemallCartExample();

        litemallCartExample.or().andGoodsIdEqualTo(goodId).andProductIdEqualTo(productId);
        LitemallCart litemallCart = litemallCartMapper.selectOneByExample(litemallCartExample);
        return litemallCart;
    }

    @Override
    public Boolean add(LitemallCart litemallCart, Integer userId) {
        Map<String,Object> map=new HashMap<>();
        Integer goodsId = litemallCart.getGoodsId();
        Integer productId = litemallCart.getProductId();
        LitemallGoods litemallGoods = litemallGoodsService.findGoodById(goodsId);
        LitemallGoodsProduct litemallGoodsProduct = litemallProductService.findByProductId(productId);
        litemallCart.setUserId(userId);
        if (litemallGoodsProduct.getNumber()==null){
            return false;
        }else {
            litemallCart.setGoodsSn(goodsId.toString());
            litemallCart.setGoodsName(litemallGoods.getName());
            litemallCart.setAddTime(LocalDateTime.now());
            litemallCart.setPrice(litemallGoodsProduct.getPrice());
            litemallCart.setSpecifications(litemallGoodsProduct.getSpecifications());
            litemallCart.setChecked(true);
            litemallCart.setPicUrl(litemallGoodsProduct.getUrl());
            litemallCart.setUpdateTime(LocalDateTime.now());
            litemallCart.setDeleted(false);
        }
        litemallCartMapper.insert(litemallCart);
        return true;
    }

    @Override
    public List<LitemallCart> findAll(Integer page,Integer size) {
        LitemallCartExample litemallCartExample=new LitemallCartExample();
        litemallCartExample.or().andDeletedEqualTo(false);
        PageHelper.startPage(page,size);
        List<LitemallCart> litemallCartList = litemallCartMapper.selectByExample(litemallCartExample);
        return litemallCartList;
    }
}
