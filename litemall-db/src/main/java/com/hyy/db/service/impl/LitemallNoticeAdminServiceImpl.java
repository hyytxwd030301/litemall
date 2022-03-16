package com.hyy.db.service.impl;

import com.hyy.db.domain.LitemallAdmin;
import com.hyy.db.domain.LitemallNoticeAdminExample;
import com.hyy.db.domain.LitemallUser;
import com.hyy.db.mapper.LitemallNoticeAdminMapper;
import com.hyy.db.service.LitemallNoticeAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LitemallNoticeAdminServiceImpl implements LitemallNoticeAdminService {

    @Autowired
    private LitemallNoticeAdminMapper litemallNoticeAdminMapper;

    @Override
    public Integer countNoReadMessage(Integer adminId) {
        LitemallNoticeAdminExample litemallNoticeAdminExample = new LitemallNoticeAdminExample();
        litemallNoticeAdminExample.or().andAdminIdEqualTo(adminId).andDeletedEqualTo(false).andReadTimeIsNull();
        Integer count = Math.toIntExact(litemallNoticeAdminMapper.countByExample(litemallNoticeAdminExample));

        return count;
    }


}
