package com.hyy.db.service.impl;

import com.hyy.db.domain.LitemallIssue;
import com.hyy.db.domain.LitemallIssueExample;
import com.hyy.db.mapper.LitemallIssueMapper;
import com.hyy.db.service.LitemallIssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LitemallIssueServiceImpl implements LitemallIssueService {

    @Autowired
    private LitemallIssueMapper litemallIssueMapper;


    @Override
    public List<LitemallIssue> findAll() {
        List<LitemallIssue> litemallIssueList = litemallIssueMapper.selectByExample(null);
        return litemallIssueList;
    }
}
