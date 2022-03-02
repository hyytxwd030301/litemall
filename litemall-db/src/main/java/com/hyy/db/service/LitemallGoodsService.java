package com.hyy.db.service;

import com.hyy.db.domain.LitemallGoods;

import java.util.List;

public interface LitemallGoodsService {
    List<LitemallGoods> findInitIsNew(Integer page,Integer size,String sort,String order);
    List<LitemallGoods> findInitIsNew(Integer page,Integer size);
    List<LitemallGoods> findInitIsHot(Integer page,Integer size,String sort,String order);
    List<LitemallGoods> findInitIsHot(Integer page,Integer size);
    List<LitemallGoods> findInitCategoryGoods(List<Integer> idList,Integer page,Integer size);
    Integer count();
}
