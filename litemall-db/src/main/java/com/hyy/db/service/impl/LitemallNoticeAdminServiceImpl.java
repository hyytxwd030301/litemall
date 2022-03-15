package com.hyy.db.service.impl;

import com.hyy.db.domain.LitemallNoticeAdminExample;
import com.hyy.db.mapper.LitemallNoticeAdminMapper;
import com.hyy.db.service.LitemallNoticeAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LitemallNoticeAdminServiceImpl implements LitemallNoticeAdminService {

    @Autowired
    private LitemallNoticeAdminMapper litemallNoticeAdminMapper;

    @Override
    public Integer countNoReadMessage() {
        LitemallNoticeAdminExample litemallNoticeAdminExample = new LitemallNoticeAdminExample();
        litemallNoticeAdminExample.or().andAdminIdEqualTo()
        return null;
    }
}
