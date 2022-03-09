package com.hyy.db.service;

import com.hyy.db.domain.LitemallUser;

public interface LitemallUserService {
    LitemallUser findByOpenId(String openId);
    Boolean insert(LitemallUser litemallUser);
    Boolean update(LitemallUser litemallUser);
}
