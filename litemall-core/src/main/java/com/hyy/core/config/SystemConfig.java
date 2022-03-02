package com.hyy.core.config;

import java.util.HashMap;
import java.util.Map;

public class SystemConfig {
    private static Map<String,String> config=new HashMap<>();
    static String LITEMALL_ORDER_UNPAID="litemall_order_unpaid";
    static String LITEMALL_WX_INDEX_NEW="litemall_wx_index_new";
    static String LITEMALL_MALL_LATITUDE="litemall_mall_latitude";
    static String LITEMALL_ORDER_UNCONFIRM="litemall_order_unconfirm";
    static String LITEMALL_WX_SHARE="litemall_wx_share";
    static String LITEMALL_EXPRESS_FREIGHT_MIN="litemall_express_freight_min";
    static String LITEMALL_MALL_NAME="litemall_mall_name";
    static String LITEMALL_EXPRESS_FREIGHT_VALUE="litemall_express_freight_value";
    static String LITEMALL_MALL_QQ="litemall_mall_qq";
    static String LITEMALL_WX_INDEX_HOT="litemall_wx_index_hot";
    static String LITEMALL_ORDER_COMMENT="litemall_order_comment";
    static String LITEMALL_WX_CATLOG_GOODS="litemall_wx_catlog_goods";
    static String LITEMALL_MALL_LONGITUDE="litemall_mall_longitude";
    static String LITEMALL_MALL_PHONE="litemall_mall_phone";
    static String LITEMALL_WX_CATLOG_LIST="litemall_wx_catlog_list";
    static String LITEMALL_MALL_ADDRESS="litemall_mall_address";
    static String LITEMALL_WX_INDEX_BRAND="litemall_wx_index_brand";
    static String LITEMALL_WX_INDEX_TOPIC="litemall_wx_index_topic";


    public static void setConfig(Map<String,String> systemConfig){
        config=systemConfig;
    }
    static Integer getValue(String name){
      return Integer.parseInt(config.get(name));

    }
    static String getStringValue(String name){
        return config.get(name);
    }
    public static Integer getBrand(){
        Integer value = getValue(LITEMALL_WX_INDEX_BRAND);
        return value;
    }
    public static Integer getGoods(){
        Integer value = getValue(LITEMALL_WX_INDEX_NEW);
        return value;
    }

    public static Integer getTopic() {
        Integer value = getValue(LITEMALL_WX_INDEX_TOPIC);
        return value;
    }

    public static Integer getCategoryGoods() {
        Integer value = getValue(LITEMALL_WX_CATLOG_GOODS);
        return value;
    }
    public static String getLitemallName(){
        String stringValue = getStringValue(LITEMALL_MALL_NAME);
        return stringValue;
    }
    public static String getLitemallAddress(){
        String stringValue = getStringValue(LITEMALL_MALL_ADDRESS);
        return stringValue;
    }
    public static String getLitemallPhoneNumber(){
        String stringValue = getStringValue(LITEMALL_MALL_PHONE);
        return stringValue;
    }
    public static String getLitemallQQ(){
        String stringValue = getStringValue(LITEMALL_MALL_QQ);
        return stringValue;
    }
}
