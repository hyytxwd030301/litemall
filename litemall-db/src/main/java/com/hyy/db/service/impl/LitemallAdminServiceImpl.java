package com.hyy.db.service.impl;

import com.hyy.db.domain.LitemallAdmin;
import com.hyy.db.domain.LitemallAdminExample;
import com.hyy.db.mapper.LitemallAdminMapper;
import com.hyy.db.service.LitemallAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LitemallAdminServiceImpl implements LitemallAdminService {
    @Autowired
    private LitemallAdminMapper litemallAdminMapper;

    @Override
    public LitemallAdmin findByUserName(String userName) {
        LitemallAdminExample litemallAdminExample=new LitemallAdminExample();
        litemallAdminExample.or().andUsernameEqualTo(userName);
        LitemallAdmin litemallAdmin = litemallAdminMapper.selectOneByExample(litemallAdminExample);

        return litemallAdmin;
    }
}
