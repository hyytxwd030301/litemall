package com.hyy.db.service.impl;

import com.hyy.db.domain.LitemallRole;
import com.hyy.db.domain.LitemallRoleExample;
import com.hyy.db.mapper.LitemallRoleMapper;
import com.hyy.db.service.LitemallRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class LitemallRoleServiceImpl implements LitemallRoleService {
    @Autowired
    private LitemallRoleMapper litemallRoleMapper;
    @Override
    public Set<String> findByRoleIds(Integer[] roleIds) {
        Set<String> roles=new HashSet<>();
        if (roleIds.length==0){
            return roles;
        }
        LitemallRoleExample litemallRoleExample=new LitemallRoleExample();
        litemallRoleExample.or().andDeletedEqualTo(false).andIdIn(Arrays.asList(roleIds)).andEnabledEqualTo(true);
        List<LitemallRole> litemallRoleList = litemallRoleMapper.selectByExample(litemallRoleExample);

        for (LitemallRole role:litemallRoleList){
            roles.add(role.getName());
        }

        return roles;
    }
}
