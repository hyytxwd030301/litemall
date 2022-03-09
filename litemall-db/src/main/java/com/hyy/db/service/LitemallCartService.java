package com.hyy.db.service;

import com.hyy.db.domain.LitemallCart;
import com.hyy.db.domain.LitemallUser;

import java.util.List;

public interface LitemallCartService {

    Integer findCountByUserId(Integer useId);

    LitemallCart findByGoodIdAndProductId(Integer goodId,Integer productId);


    Object add(LitemallCart litemallCart, Integer userId);

    List<LitemallCart> findAll(Integer page,Integer size);
}
