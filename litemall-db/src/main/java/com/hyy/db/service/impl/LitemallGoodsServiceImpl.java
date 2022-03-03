package com.hyy.db.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hyy.db.domain.LitemallCategoryExample;
import com.hyy.db.domain.LitemallGoods;
import com.hyy.db.domain.LitemallGoods.Column;
import com.hyy.db.domain.LitemallGoodsExample;
import com.hyy.db.mapper.LitemallGoodsMapper;
import com.hyy.db.service.LitemallGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LitemallGoodsServiceImpl implements LitemallGoodsService {
    @Autowired
    private LitemallGoodsMapper litemallGoodsMapper;

    private Column[] columns=new Column[]{Column.id,Column.name,Column.picUrl,Column.addTime,Column.isNew,Column.brandId,Column.brief,Column.categoryId,Column.deleted,Column.counterPrice,Column.detail,Column.gallery,Column.goodsSn,Column.isHot,Column.isOnSale,Column.keywords,Column.sortOrder,Column.updateTime,Column.unit};

    @Override
    public List<LitemallGoods> findInitIsNew(Integer page, Integer size, String sort, String order) {
        LitemallGoodsExample litemallGoodsExample=new LitemallGoodsExample();
        litemallGoodsExample.or().andIsOnSaleEqualTo(true).andIsNewEqualTo(true).andDeletedEqualTo(false);
        PageHelper.startPage(page,size);
        if (sort!=null&&order!=null){
           litemallGoodsExample.setOrderByClause(sort+" "+order);
        }
        List<LitemallGoods> litemallGoodsList = litemallGoodsMapper.selectByExampleSelective(litemallGoodsExample, columns);

        return litemallGoodsList;
    }

    @Override
    public List<LitemallGoods> findInitIsNew(Integer page, Integer size) {
        List<LitemallGoods> litemallGoodsList = findInitIsNew(page, size, "add_time", "desc");
        return litemallGoodsList;
    }

    @Override
    public List<LitemallGoods> findInitIsHot(Integer page, Integer size, String sort, String order) {
        LitemallGoodsExample litemallGoodsExample=new LitemallGoodsExample();
        litemallGoodsExample.or().andDeletedEqualTo(false).andIsOnSaleEqualTo(true).andIsHotEqualTo(true);
        PageHelper.startPage(page,size);
        if (sort!=null&&order!=null){
            litemallGoodsExample.setOrderByClause(sort+" "+order);
        }
        List<LitemallGoods> litemallGoodsList = litemallGoodsMapper.selectByExampleSelective(litemallGoodsExample, columns);
        return litemallGoodsList;
    }

    @Override
    public List<LitemallGoods> findInitIsHot(Integer page, Integer size) {
        List<LitemallGoods> litemallGoodsList = findInitIsHot(page, size, "add_time", "desc");
        return litemallGoodsList;
    }

    @Override
    public List<LitemallGoods> findInitCategoryGoods(List<Integer> idList, Integer page, Integer size) {
        LitemallGoodsExample litemallGoodsExample=new LitemallGoodsExample();
        litemallGoodsExample.or().andDeletedEqualTo(false).andIsOnSaleEqualTo(true).andIdIn(idList);
        PageHelper.startPage(page,size);
        litemallGoodsExample.setOrderByClause("add_time desc");
        List<LitemallGoods> litemallGoodsList = litemallGoodsMapper.selectByExampleSelective(litemallGoodsExample, columns);
        return litemallGoodsList;
    }

    @Override
    public List<LitemallGoods> findGoodsById(Integer categoryId,Integer page,Integer size) {
        LitemallGoodsExample litemallGoodsExample=new LitemallGoodsExample();
        litemallGoodsExample.or().andDeletedEqualTo(false).andIsOnSaleEqualTo(true).andCategoryIdEqualTo(categoryId);

        PageHelper.startPage(page,size);
        List<LitemallGoods> litemallGoodsList = litemallGoodsMapper.selectByExampleSelective(litemallGoodsExample, columns);
        return litemallGoodsList;
    }

    @Override
    public LitemallGoods findGoodById(Integer id) {
        LitemallGoodsExample litemallGoodsExample=new LitemallGoodsExample();
        litemallGoodsExample.or().andDeletedEqualTo(false);
        LitemallGoods litemallGoods = litemallGoodsMapper.selectByPrimaryKeySelective(id, columns);
        return litemallGoods;
    }

    @Override
    public List<LitemallGoods> findByCategoryId(Integer categoryId,Integer page,Integer size) {
        LitemallGoodsExample litemallGoodsExample=new LitemallGoodsExample();
        litemallGoodsExample.or().andDeletedEqualTo(false).andCategoryIdEqualTo(categoryId);
        PageHelper.startPage(page,size);
        litemallGoodsExample.setOrderByClause("add_time desc");
        List<LitemallGoods> litemallGoodsList = litemallGoodsMapper.selectByExampleSelective(litemallGoodsExample, columns);
        return litemallGoodsList;
    }

    @Override
    public Integer count() {
        LitemallGoodsExample litemallGoodsExample=new LitemallGoodsExample();
        litemallGoodsExample.or().andDeletedEqualTo(false).andIsOnSaleEqualTo(true);
        PageHelper.startPage(1,10);
        Page<LitemallGoods> litemallGoodsPage= (Page<LitemallGoods>) litemallGoodsMapper.selectByExampleSelective(litemallGoodsExample,columns);
        Integer count = Math.toIntExact(litemallGoodsPage.getTotal());
        return count;
    }


}
