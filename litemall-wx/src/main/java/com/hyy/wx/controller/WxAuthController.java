package com.hyy.wx.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.hyy.core.util.IpUtil;
import com.hyy.core.util.ResultUtil;
import com.hyy.db.domain.LitemallUser;
import com.hyy.db.service.LitemallUserService;
import com.hyy.wx.VO.UserInfo;
import com.hyy.wx.VO.WxLogInInfo;
import com.hyy.wx.util.WxJwtUtil;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wx/auth")
public class WxAuthController {
    @Autowired
    private WxMaService wxService;

    @Autowired
    private LitemallUserService litemallUserService;


    @RequestMapping("/login_by_weixin")
    public Object login_by_weixin(@RequestBody WxLogInInfo wxLogInInfo, HttpServletRequest request){

        String code = wxLogInInfo.getCode();
        UserInfo userInfo = wxLogInInfo.getUserInfo();
        WxMaJscode2SessionResult sessionInfo=null;
        try {
            sessionInfo = this.wxService.getUserService().getSessionInfo(code);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        String sessionKey=null;
        String openid=null;
        if (sessionInfo!=null){
            sessionKey= sessionInfo.getSessionKey();
            openid= sessionInfo.getOpenid();
        }

        if (sessionKey==null||openid==null){
            return ResultUtil.badArgument();
            //结束会话
        }
        //查询数据库是否有该注册信息
        LitemallUser litemallUser = litemallUserService.findByOpenId(openid);
        if (litemallUser==null){
            litemallUser=new LitemallUser();
            litemallUser.setUsername(openid);
            litemallUser.setPassword(openid);
            litemallUser.setGender(userInfo.getGender());
            litemallUser.setAvatar(userInfo.getAvatarUrl());
            litemallUser.setAddTime(LocalDateTime.now());
            litemallUser.setLastLoginTime(LocalDateTime.now());
            litemallUser.setLastLoginIp(IpUtil.getIpAddr(request));
            litemallUser.setNickname(userInfo.getNickName());
            litemallUser.setWeixinOpenid(openid);
            litemallUser.setSessionKey(sessionKey);
            Boolean falg = litemallUserService.insert(litemallUser);
        }else {
            litemallUser.setSessionKey(sessionKey);
            litemallUser.setLastLoginIp(IpUtil.getIpAddr(request));
            litemallUser.setLastLoginTime(LocalDateTime.now());
            if (litemallUserService.update(litemallUser)){
                return ResultUtil.fail(504,"更新用户数据失败");
            }
        }

        //使用jwt实现认证授权操作
        String token = WxJwtUtil.getToken(litemallUser.getId());
        Map<String,Object> map=new HashMap<>();
        map.put("token",token);
        map.put("userInfo",userInfo);
        return ResultUtil.ok(map);
    }

}
