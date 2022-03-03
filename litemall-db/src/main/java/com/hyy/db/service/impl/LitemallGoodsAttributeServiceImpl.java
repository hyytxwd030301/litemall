package com.hyy.db.service.impl;

import com.hyy.db.domain.LitemallGoodsAttribute;
import com.hyy.db.domain.LitemallGoodsAttributeExample;
import com.hyy.db.mapper.LitemallGoodsAttributeMapper;
import com.hyy.db.service.LitemallGoodsAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LitemallGoodsAttributeServiceImpl implements LitemallGoodsAttributeService {

    @Autowired
    private LitemallGoodsAttributeMapper litemallGoodsAttributeMapper;
    @Override
    public List<LitemallGoodsAttribute> findByGoodsId(Integer id) {
        LitemallGoodsAttributeExample litemallGoodsAttributeExample=new LitemallGoodsAttributeExample();
        litemallGoodsAttributeExample.or().andDeletedEqualTo(false).andGoodsIdEqualTo(id);
        List<LitemallGoodsAttribute> litemallGoodsAttributeList = litemallGoodsAttributeMapper.selectByExample(litemallGoodsAttributeExample);

        return litemallGoodsAttributeList;
    }
}
