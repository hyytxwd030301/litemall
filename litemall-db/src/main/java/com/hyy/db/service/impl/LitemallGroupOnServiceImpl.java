package com.hyy.db.service.impl;

import com.github.pagehelper.PageHelper;
import com.hyy.db.domain.LitemallGroupon;
import com.hyy.db.domain.LitemallGrouponExample;
import com.hyy.db.mapper.LitemallGrouponMapper;
import com.hyy.db.service.LitemallGroupOnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LitemallGroupOnServiceImpl implements LitemallGroupOnService {
    @Autowired
    private LitemallGrouponMapper litemallGrouponMapper;

    @Override
    public List<LitemallGroupon> findAll(LitemallGroupon litemallGroupon, Integer page, Integer size) {
        LitemallGrouponExample litemallGrouponExample=new LitemallGrouponExample();
        litemallGrouponExample.or().andDeletedEqualTo(false);
        PageHelper.startPage(page,size);
        List<LitemallGroupon> litemallGrouponList = litemallGrouponMapper.selectByExample(litemallGrouponExample);

        return litemallGrouponList;
    }
}
