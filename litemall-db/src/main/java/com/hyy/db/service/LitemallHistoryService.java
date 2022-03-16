package com.hyy.db.service;

import com.hyy.db.domain.LitemallSearchHistory;

import java.util.List;

public interface LitemallHistoryService {
    List<LitemallSearchHistory> findAll(Integer userId, String keyword, Integer page, Integer size, String sort, String order);
}
