package com.itstaredu.handler;

import com.itstaredu.entity.RuleInfo;
import com.itstaredu.utils.GsonUtil;

/**
 * Created by Administrator on 2017-12-01.
 */
public class RuleInfoHandlerDemo {
    public RuleInfo getOrderBean(String orderInfo){
        RuleInfo order = new RuleInfo();
        //  从日志信息中过滤出订单信息
        return (RuleInfo) GsonUtil.out(orderInfo,order.getClass());
    }
}
