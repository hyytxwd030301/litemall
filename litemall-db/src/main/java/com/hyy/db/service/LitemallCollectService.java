package com.hyy.db.service;

import com.hyy.db.domain.LitemallCollect;

import java.util.List;

public interface LitemallCollectService {

    Boolean add(LitemallCollect litemallCollect);

    Boolean Delete(LitemallCollect litemallCollect,Boolean delete) ;

    LitemallCollect findByUserIdAndId(Integer userId,Integer valueId);

    List<LitemallCollect> findByPageAndSize(LitemallCollect litemallCollect,Integer page,Integer size);
}
