package com.hyy.wx.VO;

import lombok.Data;
import lombok.ToString;

/**
 avatarUrl: "https://thirdwx.qlogo.cn/mmopen/vi_32/QwFIz8rCvOuHM2Vs5WZrqqu4sIyFkMibtCUUaHVJJvf441pLHuK1j4DKkvHHVgK4XLRVphsfhljY5p1h7MtEV3w/132"
 city: ""
 country: ""
 gender: 0
 language: "zh_CN"
 nickName: "浮生若梦"
 province: ""
 */
@Data
@ToString
public class UserInfo {
    private String avatarUrl;
    private String city;
    private String country;
    private Byte gender;
    private String language;
    private String nickName;
    private String province;



}
