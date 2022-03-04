package com.hyy.db.service.impl;
import com.github.pagehelper.PageHelper;
import com.hyy.db.domain.LitemallComment;
import com.hyy.db.domain.LitemallCommentExample;
import com.hyy.db.mapper.LitemallCommentMapper;
import com.hyy.db.service.LitemallCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LitemallCommentServiceImpl implements com.hyy.db.service.LitemallCommentService {

    @Autowired
    private LitemallCommentMapper litemallCommentMapper;

    @Override
    public List<LitemallComment> findByLimit(Integer valueId, Integer page, Integer size) {
        LitemallCommentExample litemallCommentExample=new LitemallCommentExample();
        litemallCommentExample.or().andValueIdEqualTo(valueId);
        litemallCommentExample.setOrderByClause("add_time desc");
        PageHelper.startPage(page,size);
        List<LitemallComment> litemallCommentList = litemallCommentMapper.selectByExample(litemallCommentExample);
        return litemallCommentList;
    }
}
