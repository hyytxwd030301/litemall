package com.hyy.db.service;

import com.hyy.db.domain.LitemallAdmin;

import java.util.List;

public interface LitemallAdminService {

    LitemallAdmin findByUserName(String userName);

    void updateByUserId(LitemallAdmin litemallAdmin);


    void updatePassword(LitemallAdmin admin);
}
