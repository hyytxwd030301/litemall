package com.hyy.db.service;


import com.hyy.db.domain.LitemallCategory;

import java.util.List;

public interface LitemallCategoryService {
    List<LitemallCategory> findInit();
    List<LitemallCategory> findInitNotRecommend();
    List<LitemallCategory> findInitNotRecommend(Integer page,Integer size);
    List<LitemallCategory> findInitByPid(Integer pid);
    List<LitemallCategory> findAllNotFatherCategory();
    LitemallCategory findById(Integer id);

}