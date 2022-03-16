package com.hyy.db.service.impl;

import com.github.pagehelper.PageHelper;
import com.hyy.db.domain.LitemallCollect;
import com.hyy.db.domain.LitemallCollect.Column;
import com.hyy.db.domain.LitemallCollectExample;
import com.hyy.db.mapper.LitemallCollectMapper;
import com.hyy.db.service.LitemallCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LitemallCollectServiceImpl implements LitemallCollectService {

    @Autowired
    private LitemallCollectMapper litemallCollectMapper;

    private Column[] columns=new Column[]{Column.id,Column.userId,Column.valueId,Column.type,Column.updateTime,Column.deleted};

    @Override
    public Boolean add(LitemallCollect litemallCollect) {
        try{
            LitemallCollectExample litemallCollectExample=new LitemallCollectExample();
            litemallCollectExample.or().andDeletedEqualTo(false);
            litemallCollect.setAddTime(LocalDateTime.now());
            litemallCollect.setUpdateTime(LocalDateTime.now());
            litemallCollectMapper.insert(litemallCollect);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public Boolean Delete(LitemallCollect litemallCollect,Boolean delete) {
        try{
            LitemallCollectExample litemallCollectExample=new LitemallCollectExample();
            litemallCollectExample.or().andDeletedEqualTo(delete);
            litemallCollect.setUpdateTime(LocalDateTime.now());
            litemallCollectMapper.insertSelective(litemallCollect);
        }catch(Exception e){
            return false;
        }

        return true;
    }

    @Override
    public LitemallCollect findByUserIdAndId(Integer userId, Integer valueId) {
        LitemallCollectExample litemallCollectExample=new LitemallCollectExample();
        litemallCollectExample.or().andUserIdEqualTo(userId).andValueIdEqualTo(valueId);
        LitemallCollect litemallCollect = litemallCollectMapper.selectOneByExample(litemallCollectExample);
        return litemallCollect;
    }

    /**
     * 实现对商品和专题收藏夹的查询
     * 通过page和size查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public List<LitemallCollect> findByPageAndSize(LitemallCollect litemallCollect,Integer page, Integer size) {
        LitemallCollectExample litemallCollectExample=new LitemallCollectExample();
        litemallCollectExample.or().andDeletedEqualTo(false).andValueIdEqualTo(litemallCollect.getUserId()).andTypeEqualTo(litemallCollect.getType());
        PageHelper.startPage(page,size);
        List<LitemallCollect> litemallCollectList = litemallCollectMapper.selectByExample(litemallCollectExample);
        return litemallCollectList;
    }

    @Override
    public List<LitemallCollect> findAll(Integer userId,Integer valueId,Integer page, Integer size, String sort, String order) {

        LitemallCollectExample litemallCollectExample=new LitemallCollectExample();
        LitemallCollectExample.Criteria criteria = litemallCollectExample.or();
        System.out.println(userId);
        if (userId!=null){
            criteria.andUserIdEqualTo(userId);
        }
        if (valueId!=null){
            criteria.andValueIdEqualTo(valueId);
        }
        litemallCollectExample = criteria.andDeletedEqualTo(false).example();
        litemallCollectExample.setOrderByClause(sort+" " +order);
        PageHelper.startPage(page,size);
        List<LitemallCollect> litemallCollectList = litemallCollectMapper.selectByExample(litemallCollectExample);
        return litemallCollectList;
    }
}
