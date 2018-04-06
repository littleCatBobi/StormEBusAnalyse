package com.itstaredu.handler;

import com.itstaredu.entity.BrowserLog;
import com.itstaredu.utils.GsonUtil;
/**
 * 日志浏览器的具体处理类
 */
public class LogBrowserHandler {
    /**
     * 具体要处理的事务类
     * @param orderInfo 输入具体要处理的类型
     * @return 浏览器对象
     */
    public BrowserLog getOrdersBean(String orderInfo) {
        BrowserLog order = new BrowserLog();
        //从日志信息中过滤出订单信息
        return (BrowserLog) GsonUtil.out(orderInfo, order.getClass());
    }
}
