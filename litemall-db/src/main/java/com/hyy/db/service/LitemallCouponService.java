package com.hyy.db.service;

import com.hyy.db.domain.LitemallCoupon;
import com.hyy.db.domain.LitemallCouponExample;
import org.apache.catalina.startup.Catalina;

import java.util.List;

public interface LitemallCouponService {
    List<LitemallCoupon> findInit(Integer page, Integer limit);
    List<LitemallCoupon> findCouponWhenLogin(Integer userId,Integer page,Integer limit);
    List<LitemallCoupon> findInit(LitemallCouponExample.Criteria criteria,Integer page,Integer limit,String sort,String order);
    List<LitemallCoupon> findInit(Integer page,Integer limit,String sort,String order);
}
