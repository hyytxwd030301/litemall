package com.hyy.db.service;

import com.hyy.db.domain.LitemallGroupon;

import java.util.List;

public interface LitemallGroupOnService {
    List<LitemallGroupon> findAll(LitemallGroupon litemallGroupon,Integer page,Integer size);
}

