package com.hyy.db.service;

import com.hyy.db.domain.LitemallComment;

import java.util.List;

public interface LitemallCommentService {
    List<LitemallComment> findByLimit(Integer valueId,Integer page,Integer size);
}
