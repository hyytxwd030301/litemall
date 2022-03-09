package com.hyy.db.service;

import com.hyy.db.domain.LitemallGoodsProduct;

import java.util.List;

public interface LitemallProductService {

    List<LitemallGoodsProduct> findAllById(Integer goodsId);
    public LitemallGoodsProduct findByProductId(Integer productId);
}
