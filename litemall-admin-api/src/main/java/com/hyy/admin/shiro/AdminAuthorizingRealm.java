package com.hyy.admin.shiro;

import com.hyy.db.domain.LitemallAdmin;
import com.hyy.db.service.LitemallAdminService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AdminAuthorizingRealm extends AuthorizingRealm {

    @Autowired
    private LitemallAdminService litemallAdminService;


    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userName = (String) authenticationToken.getPrincipal();
        LitemallAdmin litemallAdmin = litemallAdminService.findByUserName(userName);
        String password = litemallAdmin.getPassword();
        if ((litemallAdmin.getUsername()).equals(userName)){
            return new SimpleAuthenticationInfo(userName,password,ByteSource.Util.bytes(litemallAdmin.getSlat()),this.getName());
        }
        return null;
    }
}
