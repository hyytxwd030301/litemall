package com.hyy.db.service.impl;

import com.github.pagehelper.PageHelper;
import com.hyy.db.domain.LitemallCategory;
import com.hyy.db.domain.LitemallCategory.Column;
import com.hyy.db.domain.LitemallCategoryExample;
import com.hyy.db.mapper.LitemallCategoryMapper;
import com.hyy.db.service.LitemallCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LitemallCategoryServiceImpl implements LitemallCategoryService {
    @Autowired
    private LitemallCategoryMapper litemallCategoryMapper;

    private Column[] columns=new Column[]{Column.id,Column.addTime,Column.deleted,Column.desc,Column.iconUrl,Column.keywords,Column.level,Column.pid,Column.picUrl,Column.sortOrder,Column.updateTime,Column.name};

    public List<LitemallCategory> findInit(){
        LitemallCategoryExample litemallCategoryExample=new LitemallCategoryExample();
        litemallCategoryExample.or().andLevelEqualTo("L1").andDeletedEqualTo(false);
        List<LitemallCategory> litemallCategoryList = litemallCategoryMapper.selectByExample(litemallCategoryExample);
        return litemallCategoryList;
    }

    @Override
    public List<LitemallCategory> findInitNotRecommend() {
        LitemallCategoryExample litemallCategoryExample=new LitemallCategoryExample();
        litemallCategoryExample.or().andDeletedEqualTo(false).andNameNotEqualTo("推荐").andLevelEqualTo("L1");
        List<LitemallCategory> litemallCategoryList = litemallCategoryMapper.selectByExampleSelective(litemallCategoryExample, columns);
        return litemallCategoryList;
    }
    @Override
    public List<LitemallCategory> findInitNotRecommend(Integer page, Integer size) {
        LitemallCategoryExample litemallCategoryExample=new LitemallCategoryExample();
        litemallCategoryExample.or().andDeletedEqualTo(false).andNameNotEqualTo("推荐").andLevelEqualTo("L1");
        PageHelper.startPage(page,size);
        List<LitemallCategory> litemallCategoryList = litemallCategoryMapper.selectByExampleSelective(litemallCategoryExample, columns);
        return litemallCategoryList;
    }

    @Override
    public List<LitemallCategory> findInitByPid(Integer id) {
        LitemallCategoryExample litemallCategoryExample=new LitemallCategoryExample();
        litemallCategoryExample.or().andDeletedEqualTo(false).andLevelEqualTo("L2").andPidEqualTo(id);
        List<LitemallCategory> litemallCategoryList = litemallCategoryMapper.selectByExampleSelective(litemallCategoryExample, columns);
        return litemallCategoryList;
    }
}
