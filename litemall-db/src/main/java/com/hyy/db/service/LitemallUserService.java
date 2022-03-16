package com.hyy.db.service;

import com.hyy.db.domain.LitemallUser;

import java.util.List;

public interface LitemallUserService {
    LitemallUser findByOpenId(String openId);
    Boolean insert(LitemallUser litemallUser);
    Boolean update(LitemallUser litemallUser);

    List<LitemallUser> findBySelective(Integer page, Integer size, String mobile, String sort, String order);
}
