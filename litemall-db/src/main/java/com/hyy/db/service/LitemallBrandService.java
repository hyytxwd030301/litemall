package com.hyy.db.service;

import com.hyy.db.domain.LitemallBrand;

import java.util.List;

public interface LitemallBrandService {
    List<LitemallBrand> initFind(Integer page, Integer size);
    List<LitemallBrand> initFind(Integer page,Integer size,String sort,String order);
    LitemallBrand findById(Integer id);
}
