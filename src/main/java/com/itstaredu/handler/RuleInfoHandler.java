package com.itstaredu.handler;


import com.itstaredu.entity.BrowserLog;
import com.itstaredu.entity.RuleInfo;
import com.itstaredu.utils.GsonUtil;


public class RuleInfoHandler {

    public RuleInfo getOrdersBean(String orderInfo) {
        RuleInfo order = new RuleInfo();
        //从日志信息中过滤出订单信息
        return (RuleInfo) GsonUtil.out(orderInfo, order.getClass());
    }
}
