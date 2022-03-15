package com.hyy.db.utils;

import com.hyy.db.domain.LitemallOrder;

public class OrderUtil {
    /**
     *订单流程 下单成功->用户支付订单->发货->收货->用户确认收获
     * 状态码
     * 101 订单生成，未支付订单
     * 102 用户未支付订单，并取消订单
     * 103 订单过期，用户未支付订单
     * 201 用户支付订单，但商家未发货
     * 202 用户支付订单，但取消订单，申请退款
     * 301 商家发货 ，未确认
     * 401用户确认
     * 402 用户未确认，但快递物流反馈已经收货，超过一定时间系统自动收获
     *
     *
     *
     */

    static int CREATEORDER_NOPAY=101;
    static int CANCELORDER_NOPAY=102;
    static int EXPIREDORDER_NOPAY=103;
    static int PAID_NOSHIP=201;
    static int RETURNGOODS_PAID=202;
    static int SHIP_NOCONFIRM=301;
    static int USERCONFIRM=401;
    static int AUTOMATIC_RECEIPT=402;
    public static Boolean isCreateStatus(LitemallOrder litemallOrder){
        return CREATEORDER_NOPAY==litemallOrder.getOrderStatus();
    }
    public static Boolean isPayStatus(LitemallOrder litemallOrder){
        return PAID_NOSHIP==litemallOrder.getOrderStatus();
    }
    public static Boolean isShipStatus(LitemallOrder litemallOrder){
        return SHIP_NOCONFIRM==litemallOrder.getOrderStatus();
    }

    public static Boolean isConfirmStatus(LitemallOrder litemallOrder){
        return USERCONFIRM==litemallOrder.getOrderStatus();
    }

}
