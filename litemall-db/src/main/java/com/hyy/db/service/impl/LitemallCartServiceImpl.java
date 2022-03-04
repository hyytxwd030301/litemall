package com.hyy.db.service.impl;

import com.github.pagehelper.Page;
import com.hyy.db.domain.LitemallCart;
import com.hyy.db.domain.LitemallCartExample;
import com.hyy.db.mapper.LitemallCartMapper;
import com.hyy.db.service.LitemallCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LitemallCartServiceImpl implements LitemallCartService {

    @Autowired
    private LitemallCartMapper litemallCartMapper;

    @Override
    public Integer findCountByUserId(Integer userId) {
        LitemallCartExample litemallCartExample=new LitemallCartExample();
        litemallCartExample.or().andDeletedEqualTo(false).andUserIdEqualTo(userId);
        Page<LitemallCart> litemallCartPage= (Page<LitemallCart>) litemallCartMapper.selectByExample(litemallCartExample);
        Integer total = Math.toIntExact(litemallCartPage.getTotal());
        return total;
    }
}
