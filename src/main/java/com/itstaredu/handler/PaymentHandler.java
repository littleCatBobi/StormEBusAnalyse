package com.itstaredu.handler;

import com.itstaredu.entity.BrowserLog;
import com.itstaredu.entity.PaymentInfo;
import com.itstaredu.utils.GsonUtil;
/**
 * 支付信息的具体处理类
 */
public class PaymentHandler {
    /**
     * @param orderInfo 输入的订单数据
     * @return 获取处理和分析的数据
     */
    public PaymentInfo getOrdersBean(String orderInfo) {
        PaymentInfo order = new PaymentInfo();
        //从日志信息中过滤出订单信息
        return (PaymentInfo) GsonUtil.out(orderInfo, order.getClass());
    }
}
