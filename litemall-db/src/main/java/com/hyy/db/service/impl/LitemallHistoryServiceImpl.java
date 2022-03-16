package com.hyy.db.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hyy.db.domain.LitemallSearchHistory;
import com.hyy.db.domain.LitemallSearchHistoryExample;
import com.hyy.db.mapper.LitemallSearchHistoryMapper;
import com.hyy.db.service.LitemallHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LitemallHistoryServiceImpl implements LitemallHistoryService {
    @Autowired
    private LitemallSearchHistoryMapper litemallSearchHistoryMapper;

    @Override
    public List<LitemallSearchHistory> findAll(Integer userId, String keyword, Integer page, Integer size, String sort, String order) {
        LitemallSearchHistoryExample litemallSearchHistoryExample=new LitemallSearchHistoryExample();
        LitemallSearchHistoryExample.Criteria criteria = litemallSearchHistoryExample.createCriteria();
        if (userId!=null){
            criteria.andUserIdEqualTo(userId);
        }
        if (!keyword.isEmpty()){
            criteria.andKeywordEqualTo(keyword);
        }
        criteria.andDeletedEqualTo(false);
        LitemallSearchHistoryExample example = criteria.example();
        example.setOrderByClause(sort+" "+order);
        PageHelper.startPage(page,size);
        List<LitemallSearchHistory> litemallSearchHistoryList = litemallSearchHistoryMapper.selectByExample(example);
        return litemallSearchHistoryList;
    }
}
