package com.hyy.db.service.impl;

import com.hyy.db.domain.LitemallAd;
import com.hyy.db.domain.LitemallAdExample;
import com.hyy.db.mapper.LitemallAdMapper;
import com.hyy.db.service.LitemallAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LitemallAdServiceImpl implements LitemallAdService {
    @Autowired
    private LitemallAdMapper litemallAdMapper;

    public List<LitemallAd> index(){
        LitemallAdExample litemallAdExample=new LitemallAdExample();
        litemallAdExample.or().andDeletedEqualTo(false).andEnabledEqualTo(true).andPositionEqualTo((byte) 1);
        List<LitemallAd> litemallAdList = litemallAdMapper.selectByExample(litemallAdExample);
        return litemallAdList;

    }

}
