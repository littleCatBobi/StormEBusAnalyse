package com.itstaredu.entity;

import java.io.Serializable;
/**
 * 规则pojo类
 */
public class RuleInfo implements Serializable {

    private static final long serialVersionUID = -4557389219941707957L;
    private String isNormalIp; //用户是否在常用ip下单
    private String isNormalDevice; //用户是否在常用设备上下单
    private String isNormalAddress; //用户收货地址是否是常用收货地址
    private String isNormalMobile; //用户收获手机号是否是常用手机号
    private String isChangeAccountPassword; //用户近期是否修改过登录密码
    private String isChangePaymentPassword; //用户近期是否修改过支付密码
    private String isChangeMobile; //用户近期是否修改过手机号码
    private String isCashonDelivery; //订单是否是到货付款
    private String orderproductNumByPrice; //订单中指定价格的商品数量是否满足阈值
    private String orderproductNumByCategory; //订单中指定类目的商品价格是否满足阈值
    private String orderAmount; //订单的总价值
    private long timeStamp;//时间戳

    public String getIsNormalIp() {
        return isNormalIp;
    }

    public void setIsNormalIp(String isNormalIp) {
        this.isNormalIp = isNormalIp;
    }

    public String getIsNormalDevice() {
        return isNormalDevice;
    }

    public void setIsNormalDevice(String isNormalDevice) {
        this.isNormalDevice = isNormalDevice;
    }

    public String getIsNormalAddress() {
        return isNormalAddress;
    }

    public void setIsNormalAddress(String isNormalAddress) {
        this.isNormalAddress = isNormalAddress;
    }

    public String getIsNormalMobile() {
        return isNormalMobile;
    }

    public void setIsNormalMobile(String isNormalMobile) {
        this.isNormalMobile = isNormalMobile;
    }

    public String getIsChangeAccountPassword() {
        return isChangeAccountPassword;
    }

    public void setIsChangeAccountPassword(String isChangeAccountPassword) {
        this.isChangeAccountPassword = isChangeAccountPassword;
    }

    public String getIsChangePaymentPassword() {
        return isChangePaymentPassword;
    }

    public void setIsChangePaymentPassword(String isChangePaymentPassword) {
        this.isChangePaymentPassword = isChangePaymentPassword;
    }

    public String getIsChangeMobile() {
        return isChangeMobile;
    }

    public void setIsChangeMobile(String isChangeMobile) {
        this.isChangeMobile = isChangeMobile;
    }

    public String getIsCashonDelivery() {
        return isCashonDelivery;
    }

    public void setIsCashonDelivery(String isCashonDelivery) {
        this.isCashonDelivery = isCashonDelivery;
    }

    public String getOrderproductNumByPrice() {
        return orderproductNumByPrice;
    }

    public void setOrderproductNumByPrice(String orderproductNumByPrice) {
        this.orderproductNumByPrice = orderproductNumByPrice;
    }

    public String getOrderproductNumByCategory() {
        return orderproductNumByCategory;
    }

    public void setOrderproductNumByCategory(String orderproductNumByCategory) {
        this.orderproductNumByCategory = orderproductNumByCategory;
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "RuleInfo{" +
                "isNormalIp='" + isNormalIp + '\'' +
                ", isNormalDevice='" + isNormalDevice + '\'' +
                ", isNormalAddress='" + isNormalAddress + '\'' +
                ", isNormalMobile='" + isNormalMobile + '\'' +
                ", isChangeAccountPassword='" + isChangeAccountPassword + '\'' +
                ", isChangePaymentPassword='" + isChangePaymentPassword + '\'' +
                ", isChangeMobile='" + isChangeMobile + '\'' +
                ", isCashonDelivery='" + isCashonDelivery + '\'' +
                ", orderproductNumByPrice='" + orderproductNumByPrice + '\'' +
                ", orderproductNumByCategory='" + orderproductNumByCategory + '\'' +
                ", orderAmount='" + orderAmount + '\'' +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
