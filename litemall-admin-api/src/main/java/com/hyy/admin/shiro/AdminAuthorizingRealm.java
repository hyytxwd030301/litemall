package com.hyy.admin.shiro;

import com.hyy.db.domain.LitemallAdmin;
import com.hyy.db.service.LitemallAdminService;
import com.hyy.db.service.LitemallPermissionService;
import com.hyy.db.service.LitemallRoleService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

public class AdminAuthorizingRealm extends AuthorizingRealm {

    @Autowired
    private LitemallAdminService litemallAdminService;

    @Autowired
    private LitemallRoleService litemallRoleService;

    @Autowired
    private LitemallPermissionService litemallPermissionService;


    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        if (principalCollection==null){
            new Exception("no useful information");
        }
        LitemallAdmin admin = (LitemallAdmin) getAvailablePrincipal(principalCollection);
        Integer[] roleIds = admin.getRoleIds();
        Set<String> roles = litemallRoleService.findByRoleIds(roleIds);
        Set<String> permissions = litemallPermissionService.findRoleIds(roleIds);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(roles);
        simpleAuthorizationInfo.setStringPermissions(permissions);

        return simpleAuthorizationInfo;
    }
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userName = (String) authenticationToken.getPrincipal();
        LitemallAdmin litemallAdmin = litemallAdminService.findByUserName(userName);
        String password = litemallAdmin.getPassword();
        if ((litemallAdmin.getUsername()).equals(userName)){
            return new SimpleAuthenticationInfo(litemallAdmin,password,ByteSource.Util.bytes(litemallAdmin.getSlat()),this.getName());
        }
        return null;
    }
}
