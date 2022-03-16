package com.hyy.db.service.impl;

import com.github.pagehelper.PageHelper;
import com.hyy.db.domain.LitemallFootprint;
import com.hyy.db.domain.LitemallFootprintExample;
import com.hyy.db.domain.LitemallFootprintExample.Criteria;
import com.hyy.db.mapper.LitemallFootprintMapper;
import com.hyy.db.service.LitemallFootPrintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LitemallFootPrintServiceImpl implements LitemallFootPrintService {
    @Autowired
    private LitemallFootprintMapper litemallFootprintMapper;
    @Override
    public List<LitemallFootprint> findAll(Integer userId, Integer goodsId, Integer page, Integer size, String sort, String order) {
        LitemallFootprintExample litemallFootprintExample=new LitemallFootprintExample();
        Criteria criteria = litemallFootprintExample.createCriteria();
        if (userId!=null){
            criteria.andUserIdEqualTo(userId);
        }
        if (goodsId!=null){
            criteria.andGoodsIdEqualTo(goodsId);
        }
        criteria.andDeletedEqualTo(false);
        LitemallFootprintExample example = criteria.example();
        example.setOrderByClause(sort+" "+order);
        PageHelper.startPage(page,size);
        List<LitemallFootprint> litemallFootprintList = litemallFootprintMapper.selectByExample(litemallFootprintExample);
        return litemallFootprintList;
    }
}
