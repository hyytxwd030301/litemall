package com.hyy.db.service.impl;

import com.hyy.db.domain.LitemallGoodsSpecification;
import com.hyy.db.domain.LitemallGoodsSpecificationExample;
import com.hyy.db.mapper.LitemallGoodsSpecificationMapper;
import com.hyy.db.service.LitemallGoodsSpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LitemallGoodsSpecificationServiceImpl implements LitemallGoodsSpecificationService {
    @Autowired
    private LitemallGoodsSpecificationMapper litemallGoodsSpecificationMapper;
    @Override
    public List<LitemallGoodsSpecification> findByGoodsId(Integer id) {
        LitemallGoodsSpecificationExample litemallGoodsSpecificationExample=new LitemallGoodsSpecificationExample();
        litemallGoodsSpecificationExample.or().andDeletedEqualTo(false).andGoodsIdEqualTo(id);
        List<LitemallGoodsSpecification> litemallGoodsSpecificationList = litemallGoodsSpecificationMapper.selectByExample(litemallGoodsSpecificationExample);
        return litemallGoodsSpecificationList;
    }
}
