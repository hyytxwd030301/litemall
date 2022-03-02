package com.hyy.db.service;

import com.hyy.db.domain.LitemallTopic;

import java.util.List;

public interface LitemallTopicService {
    public List<LitemallTopic> findInit(Integer page,Integer size,String sort, String order);
    public List<LitemallTopic> findInit(Integer page,Integer size);
}
