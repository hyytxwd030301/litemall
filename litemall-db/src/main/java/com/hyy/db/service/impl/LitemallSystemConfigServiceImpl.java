package com.hyy.db.service.impl;

import com.hyy.db.domain.LitemallSystem;
import com.hyy.db.domain.LitemallSystemExample;
import com.hyy.db.mapper.LitemallSystemMapper;
import com.hyy.db.service.LitemallSystemConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LitemallSystemConfigServiceImpl implements LitemallSystemConfigService {
    @Autowired
    private LitemallSystemMapper litemallSystemMapper;
    @Override
    public List<LitemallSystem> findAll() {
        LitemallSystemExample litemallSystemExample=new LitemallSystemExample();
        litemallSystemExample.or().andDeletedEqualTo(false);
        List<LitemallSystem> litemallSystemList = litemallSystemMapper.selectByExample(litemallSystemExample);
        return litemallSystemList;
    }
}
