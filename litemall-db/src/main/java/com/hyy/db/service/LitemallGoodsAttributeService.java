package com.hyy.db.service;

import com.hyy.db.domain.LitemallGoodsAttribute;

import java.util.List;

public interface LitemallGoodsAttributeService {
    List<LitemallGoodsAttribute> findByGoodsId(Integer goodsId);
}
