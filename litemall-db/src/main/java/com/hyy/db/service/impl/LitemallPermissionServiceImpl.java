package com.hyy.db.service.impl;

import com.hyy.db.domain.LitemallPermission;
import com.hyy.db.domain.LitemallPermissionExample;
import com.hyy.db.mapper.LitemallPermissionMapper;
import com.hyy.db.mapper.LitemallRoleMapper;
import com.hyy.db.service.LitemallPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class LitemallPermissionServiceImpl implements LitemallPermissionService {

    @Autowired
    private LitemallPermissionMapper litemallPermissionMapper;

    @Override
    public Set<String> findRoleIds(Integer[] roleIds) {
        Set<String> permissions=new HashSet<>();
        if (roleIds.length==0){
            return permissions;
        }
        LitemallPermissionExample litemallPermissionExample=new LitemallPermissionExample();
        litemallPermissionExample.or().andDeletedEqualTo(false).andRoleIdIn(Arrays.asList(roleIds));
        List<LitemallPermission> litemallPermissionList = litemallPermissionMapper.selectByExample(litemallPermissionExample);
        for (LitemallPermission litemallPermission : litemallPermissionList){
            permissions.add(litemallPermission.getPermission());
        }
        return permissions;
    }
}
