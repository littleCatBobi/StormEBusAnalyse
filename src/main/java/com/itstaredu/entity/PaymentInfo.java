package com.itstaredu.entity;

import java.io.Serializable;
/**
 * 支付pojo类
 */
public class PaymentInfo implements Serializable {

    private static final long serialVersionUID = 4354463869840318417L;
    private String orderId; //订单编号
    private String createOrderTime; //创建订单时间
    private String paymentId; //支付编号
    private String paymentTime; //支付时间
    private String productId; //商品编号
    private String productName; //商品名称
    private String productPrice; //商品价格
    private String promotionPrice; //促销价格
    private String shopId; //商品编号
    private String shopName; //商铺名称
    private String shopMobilePhone; //商铺手机号
    private String num; //订单数量
    private long timeStamp;//时间戳

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCreateOrderTime() {
        return createOrderTime;
    }

    public void setCreateOrderTime(String createOrderTime) {
        this.createOrderTime = createOrderTime;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(String promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopMobilePhone() {
        return shopMobilePhone;
    }

    public void setShopMobilePhone(String shopMobilePhone) {
        this.shopMobilePhone = shopMobilePhone;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "PaymentInfo{" +
                "orderId='" + orderId + '\'' +
                ", createOrderTime='" + createOrderTime + '\'' +
                ", paymentId='" + paymentId + '\'' +
                ", paymentTime='" + paymentTime + '\'' +
                ", productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", productPrice='" + productPrice + '\'' +
                ", promotionPrice='" + promotionPrice + '\'' +
                ", shopId='" + shopId + '\'' +
                ", shopName='" + shopName + '\'' +
                ", shopMobilePhone='" + shopMobilePhone + '\'' +
                ", num='" + num + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                '}';
    }
}
