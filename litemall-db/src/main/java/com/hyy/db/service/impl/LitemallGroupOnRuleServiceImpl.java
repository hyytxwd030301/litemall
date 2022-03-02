package com.hyy.db.service.impl;

import com.github.pagehelper.PageHelper;
import com.hyy.db.domain.LitemallGrouponRules;
import com.hyy.db.domain.LitemallGrouponRules.Column;
import com.hyy.db.domain.LitemallGrouponRulesExample;
import com.hyy.db.mapper.LitemallGrouponRulesMapper;
import com.hyy.db.service.LitemallGroupOnRuleService;
import com.hyy.db.utils.GrouponConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LitemallGroupOnRuleServiceImpl implements LitemallGroupOnRuleService {

    @Autowired
    private LitemallGrouponRulesMapper litemallGrouponRulesMapper;


    @Override
    public List<LitemallGrouponRules> findInit(Integer page,Integer size,String sort,String order) {
        LitemallGrouponRulesExample litemallGrouponRulesExample=new LitemallGrouponRulesExample();
        litemallGrouponRulesExample.or().andDeletedEqualTo(false).andStatusEqualTo(GrouponConstant.RULE_STATUS_ON);
        PageHelper.startPage(page,size);
        List<LitemallGrouponRules> litemallGrouponRulesList = litemallGrouponRulesMapper.selectByExample(litemallGrouponRulesExample);
        return litemallGrouponRulesList;
    }

    @Override
    public List<LitemallGrouponRules> findInit(Integer page, Integer size) {
        List<LitemallGrouponRules> litemallGrouponRulesList = findInit(page, size, "add_time", "desc");
        return litemallGrouponRulesList;
    }
}
