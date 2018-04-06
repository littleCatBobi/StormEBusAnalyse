package com.itstaredu.bolt;

import com.itstaredu.entity.BrowserLog;
import com.itstaredu.handler.LogBrowserHandler;
import com.itstaredu.utils.GsonUtil;
import com.itstaredu.utils.KafkaUtil;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;
import java.util.Map;
/**
 * 分析Browser的bolt类
 */
public class BrowserAnalyseBolt extends BaseRichBolt {

    private OutputCollector collector;
    LogBrowserHandler loginfohandler;
    private KafkaUtil kafkaUtil;
    private static final String TOPIC = "browserLogResult";

    public void execute(Tuple tuple) {
        String orderInfo = tuple.getString(0);
        System.out.println(orderInfo);
        //过滤出订单信息
        BrowserLog order = loginfohandler.getOrdersBean(orderInfo);
        System.err.println("---------------------------------------" + order.toString());
        //store the salesByMerchant infomation into Kafka
        kafkaUtil.send(GsonUtil.in(order),TOPIC);
    }

    @Override
    public void prepare(Map map, TopologyContext topologyContext, org.apache.storm.task.OutputCollector outputCollector) {
        this.collector = collector;
        this.loginfohandler = new LogBrowserHandler();
        this.kafkaUtil = new KafkaUtil();

    }

    public void declareOutputFields(OutputFieldsDeclarer arg0) {

    }
}
