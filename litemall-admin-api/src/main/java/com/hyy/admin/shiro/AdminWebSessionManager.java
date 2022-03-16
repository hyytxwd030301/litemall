package com.hyy.admin.shiro;

import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

public class AdminWebSessionManager extends DefaultWebSessionManager {
    private final String TOKENNAME="TOKEN";
    public AdminWebSessionManager(){
        super();
        this.setGlobalSessionTimeout(100000000000L);
    }

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        String sessionId = WebUtils.toHttp(request).getHeader(TOKENNAME);
        if (sessionId!=null){
            return sessionId;
        }
        return super.getSessionId(request, response);
    }
}
