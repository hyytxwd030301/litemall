package com.hyy.db.service.impl;

import com.github.pagehelper.PageHelper;
import com.hyy.db.domain.LitemallAddress;
import com.hyy.db.domain.LitemallAddressExample;
import com.hyy.db.mapper.LitemallAddressMapper;
import com.hyy.db.service.LitemallAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LitemallAddressServiceImpl implements LitemallAddressService {

    @Autowired
    private LitemallAddressMapper litemallAddressMapper;
    @Override
    public List<LitemallAddress> findAllByPageAndSize(Integer userId, Integer page, Integer size,String city) {
        LitemallAddressExample litemallAddressExample=new LitemallAddressExample();
        litemallAddressExample.or().andDeletedEqualTo(false).andUserIdEqualTo(userId).andCityEqualTo(city);
        PageHelper.startPage(page,size);
        List<LitemallAddress> litemallAddressList = litemallAddressMapper.selectByExample(litemallAddressExample);
        return litemallAddressList;
    }

    @Override
    public Boolean add(LitemallAddress litemallAddress) {
        try{
            litemallAddress.setAddTime(LocalDateTime.now());
            litemallAddress.setUpdateTime(LocalDateTime.now());
            litemallAddressMapper.insert(litemallAddress);
            litemallAddressMapper.insert(litemallAddress);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
