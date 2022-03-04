package com.hyy.db.service.impl;

import com.hyy.db.domain.LitemallBrand;
import com.hyy.db.domain.LitemallBrand.Column;
import com.hyy.db.domain.LitemallBrandExample;
import com.hyy.db.mapper.LitemallBrandMapper;
import com.hyy.db.service.LitemallBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 品牌制造商直供商品
 */

@Service
public class LitemallBrandServiceImpl implements LitemallBrandService {
    @Autowired
    private LitemallBrandMapper litemallBrandMapper;
    private LitemallBrand.Column[] columns = new LitemallBrand.Column[]{LitemallBrand.Column.id, Column.name, Column.desc, Column.picUrl, Column.floorPrice};


    @Override
    public List<LitemallBrand> initFind(Integer page, Integer size) {
        return initFind(page,size,null,null);
    }

    @Override
    public List<LitemallBrand> initFind(Integer page,Integer size,String sort,String order) {
        LitemallBrandExample litemallBrandExample=new LitemallBrandExample();
        litemallBrandExample.or().andDeletedEqualTo(false);
        if (sort!=null&&order!=null){
            litemallBrandExample.setOrderByClause(sort+" "+order);
        }


        List<LitemallBrand> litemallBrandList = litemallBrandMapper.selectByExampleSelective(litemallBrandExample,columns);
        return litemallBrandList;
    }

    @Override
    public LitemallBrand findById(Integer id) {
        LitemallBrand litemallBrand = litemallBrandMapper.selectByPrimaryKeySelective(id, columns);
        return litemallBrand;
    }
}
