package com.itstaredu.bolt;

import com.itstaredu.entity.RuleInfo;
import com.itstaredu.handler.RuleInfoHandler;
import com.itstaredu.utils.GsonUtil;
import com.itstaredu.utils.KafkaUtil;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;
import java.util.Map;
/**
 * 规则信息分析bolt类
 */
public class RuleInfoAnalyseBolt extends BaseRichBolt {
    private OutputCollector collector;
    RuleInfoHandler ruleInfoHandler;
    private KafkaUtil kafkaUtil;
    private static final String TOPIC = "ruleInfoResult";

    public void execute(Tuple tuple) {
        String orderInfo = tuple.getString(0);
        System.out.println(orderInfo);
        RuleInfo order = ruleInfoHandler.getOrdersBean(orderInfo);
        System.err.println("---------------------------------------" + order.toString());
        //store the salesByMerchant infomation into Kafka
        kafkaUtil.send(GsonUtil.in(order), TOPIC);
    }

    @Override
    public void prepare(Map map, TopologyContext topologyContext, org.apache.storm.task.OutputCollector outputCollector) {
        this.collector = collector;
        this.ruleInfoHandler = new RuleInfoHandler();
        this.kafkaUtil = new KafkaUtil();
    }

    public void declareOutputFields(OutputFieldsDeclarer arg0) {

    }
}