package com.hyy.db.service;


import java.util.Set;

public interface LitemallPermissionService {
    Set<String> findRoleIds(Integer[] roleIds);
}
