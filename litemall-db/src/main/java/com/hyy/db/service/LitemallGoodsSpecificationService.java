package com.hyy.db.service;

import com.hyy.db.domain.LitemallGoodsSpecification;

import java.util.List;

public interface LitemallGoodsSpecificationService {
    List<LitemallGoodsSpecification> findByGoodsId(Integer id);
}
