package com.itstaredu.bolt;

import com.itstaredu.entity.RuleInfo;
import com.itstaredu.handler.RuleInfoHandler;
import com.itstaredu.utils.GsonUtil;
import com.itstaredu.utils.KafkaUtil;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;

import java.util.Map;

/**
 * 规则信息分析bolt类，通过bolt去处理Spout的数据，然后将结果返回至kafka
 */
public class RuleInfoAnalysisDemo  extends BaseRichBolt{
    private OutputCollector collector;
    RuleInfoHandler ruleInfoHandler;
    private KafkaUtil kafkaUtil;
    // 定义TOPIC 类型，将Topic的name设置为ruleInfoResult
    private final String TOPIC = "ruleInfoResult";

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.collector = collector;
        this.ruleInfoHandler = new RuleInfoHandler();
        this.kafkaUtil = new KafkaUtil();
    }

    @Override
    public void execute(Tuple tuple) {
        String orderInfo = tuple.getString(0);
        RuleInfo order = ruleInfoHandler.getOrdersBean(orderInfo);
        kafkaUtil.send(GsonUtil.in(order),TOPIC);
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }
}
