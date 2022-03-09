package com.hyy.wx.VO;

import lombok.Data;
import org.apache.catalina.User;

@Data
public class WxLogInInfo {
    private String code;
    private UserInfo userInfo;
}
