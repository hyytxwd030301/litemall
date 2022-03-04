package com.hyy.db.service.impl;

import com.hyy.db.domain.LitemallGoodsProduct;
import com.hyy.db.domain.LitemallGoodsProductExample;
import com.hyy.db.mapper.LitemallGoodsProductMapper;
import com.hyy.db.service.LitemallProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LitemallProductServiceImpl implements LitemallProductService {
    @Autowired
    private LitemallGoodsProductMapper litemallGoodsProductMapper;

    @Override
    public List<LitemallGoodsProduct> findAllById(Integer goodsId) {
        LitemallGoodsProductExample litemallGoodsProductExample=new LitemallGoodsProductExample();
        litemallGoodsProductExample.or().andDeletedEqualTo(false).andGoodsIdEqualTo(goodsId);
        litemallGoodsProductExample.setOrderByClause("add_time desc");
        List<LitemallGoodsProduct> litemallGoodsProductList = litemallGoodsProductMapper.selectByExample(litemallGoodsProductExample);
        return litemallGoodsProductList;
    }
}
