package com.hyy.db.service;

import java.util.Set;

public interface LitemallRoleService {
    Set<String> findByRoleIds(Integer[] roleIds);
}
