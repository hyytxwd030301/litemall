package com.hyy.db.service;

import com.hyy.db.domain.LitemallFootprint;

import java.util.List;

public interface LitemallFootPrintService {
    List<LitemallFootprint> findAll(Integer userId, Integer valueId, Integer page, Integer size, String sort, String order);
}
