package com.hyy.db.service.impl;

import com.hyy.db.mapper.LitemallCollectMapper;
import com.hyy.db.service.LitemallCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LitemallCollectServiceImpl implements LitemallCollectService {

    @Autowired
    private LitemallCollectMapper litemallCollectMapper;
}
