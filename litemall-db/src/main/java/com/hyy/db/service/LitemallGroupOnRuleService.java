package com.hyy.db.service;

import com.hyy.db.domain.LitemallGrouponRules;

import java.util.List;

public interface LitemallGroupOnRuleService {
    List<LitemallGrouponRules> findInit(Integer page,Integer size,String sort,String order);
    List<LitemallGrouponRules> findInit(Integer page,Integer size);
}
