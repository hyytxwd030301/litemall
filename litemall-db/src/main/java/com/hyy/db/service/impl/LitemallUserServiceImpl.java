package com.hyy.db.service.impl;

import com.hyy.db.domain.LitemallUser;
import com.hyy.db.domain.LitemallUserExample;
import com.hyy.db.mapper.LitemallUserMapper;
import com.hyy.db.service.LitemallUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LitemallUserServiceImpl implements LitemallUserService {
    @Autowired
    private LitemallUserMapper litemallUserMapper;


    @Override
    public LitemallUser findByOpenId(String openId) {
        LitemallUserExample litemallUserExample=new LitemallUserExample();
        litemallUserExample.or().andLogicalDeleted(false).andWeixinOpenidEqualTo(openId);
        LitemallUser litemallUser = litemallUserMapper.selectOneByExample(litemallUserExample);
        return litemallUser;
    }

    @Override
    public Boolean insert(LitemallUser litemallUser) {
        try {
            litemallUserMapper.insert(litemallUser);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;

    }

    @Override
    public Boolean update(LitemallUser litemallUser) {
        try {
            litemallUserMapper.updateByPrimaryKey(litemallUser);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
