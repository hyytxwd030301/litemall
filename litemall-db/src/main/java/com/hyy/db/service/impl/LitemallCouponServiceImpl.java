package com.hyy.db.service.impl;

import com.github.pagehelper.PageHelper;
import com.hyy.db.domain.LitemallCoupon;
import com.hyy.db.domain.LitemallCoupon.*;
import com.hyy.db.domain.LitemallCouponExample;
import com.hyy.db.domain.LitemallCouponUser;
import com.hyy.db.domain.LitemallCouponUserExample;
import com.hyy.db.mapper.LitemallCouponMapper;
import com.hyy.db.mapper.LitemallCouponUserMapper;
import com.hyy.db.service.LitemallCouponService;
import com.hyy.db.utils.CouponConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LitemallCouponServiceImpl implements LitemallCouponService {
    @Autowired
    private LitemallCouponMapper litemallCouponMapper;

    @Autowired
    private LitemallCouponUserMapper litemallCouponUserMapper;

    private LitemallCoupon.Column[] result = new Column[]{Column.id, Column.name, Column.desc, Column.tag,
            Column.days, Column.startTime, Column.endTime,
            Column.discount, Column.min};

    @Override
    public List<LitemallCoupon> findInit(Integer page, Integer limit) {
        List<LitemallCoupon> litemallCouponList = findInit(page, limit, "add_time", "desc");

        return litemallCouponList;
    }

    @Override
    public List<LitemallCoupon> findCouponWhenLogin(Integer userId, Integer page, Integer limit) {
        LitemallCouponExample.Criteria criteria=LitemallCouponExample.newAndCreateCriteria();
        LitemallCouponUserExample litemallCouponUserExample=new LitemallCouponUserExample();
        litemallCouponUserExample.or().andUser_idEqualTo(userId);
        List<LitemallCouponUser> litemallCouponUserList = litemallCouponUserMapper.selectByExample(litemallCouponUserExample);
        if (litemallCouponUserList!=null&&!litemallCouponUserList.isEmpty()){
            criteria.andIdNotIn(litemallCouponUserList.stream().map(LitemallCouponUser::getCoupon_id).collect(Collectors.toList()));
        }
        List<LitemallCoupon> litemallCouponList = findInit(criteria, page, limit, "add_time", "desc");
        return litemallCouponList;
    }

    @Override
    public List<LitemallCoupon> findInit(LitemallCouponExample.Criteria criteria, Integer page, Integer limit, String sort, String order) {
        criteria.andDeletedEqualTo(false).andTimeTypeEqualTo(CouponConstant.TYPE_COMMON).andStatusEqualTo(CouponConstant.STATUS_NORMAL);
        criteria.example().setOrderByClause(sort+" "+order);
        PageHelper.startPage(page,limit);
        List<LitemallCoupon> litemallCouponList = litemallCouponMapper.selectByExampleSelective(criteria.example(), result);
        return litemallCouponList;
    }

    @Override
    public List<LitemallCoupon> findInit(Integer page, Integer limit, String sort, String order) {
        LitemallCouponExample.Criteria criteria=LitemallCouponExample.newAndCreateCriteria();
        List<LitemallCoupon> litemallCouponList = findInit(criteria, page, limit, sort, order);
        return litemallCouponList;
    }

}
