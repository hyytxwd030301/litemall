package com.hyy.db.service.impl;

import com.github.pagehelper.PageHelper;
import com.hyy.db.domain.LitemallTopic;
import com.hyy.db.domain.LitemallTopic.Column;
import com.hyy.db.domain.LitemallTopicExample;
import com.hyy.db.mapper.LitemallTopicMapper;
import com.hyy.db.service.LitemallTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LitemallTopicServiceImpl implements LitemallTopicService {
    @Autowired
    private LitemallTopicMapper litemallTopicMapper;

    private Column[] columns=new Column[]{Column.id,Column.picUrl,Column.price,Column.content,Column.addTime,Column.deleted,Column.goods,Column.readCount,Column.sortOrder,Column.subtitle,Column.updateTime};
    @Override
    public List<LitemallTopic> findInit(Integer page, Integer size, String sort, String order) {
        LitemallTopicExample litemallTopicExample=new LitemallTopicExample();
        litemallTopicExample.or().andDeletedEqualTo(false);

        if (sort!=null&&order!=null){
            litemallTopicExample.setOrderByClause(sort+" "+order);
        }
        PageHelper.startPage(page,size);
        List<LitemallTopic> litemallTopicList = litemallTopicMapper.selectByExampleSelective(litemallTopicExample, columns);

        return litemallTopicList;
    }

    @Override
    public List<LitemallTopic> findInit(Integer page, Integer size) {
        List<LitemallTopic> litemallTopicList = findInit(page, size, "sort_order", "desc");
        return litemallTopicList;
    }
}
