package com.itstaredu.bolt;

import com.itstaredu.entity.PaymentInfo;
import com.itstaredu.handler.PaymentHandler;
import com.itstaredu.utils.GsonUtil;
import com.itstaredu.utils.KafkaUtil;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;
import java.util.Map;
/**
 * 分析订单的Bolt类
 */
public class PaymentAnalyseBolt extends BaseRichBolt{
    private OutputCollector collector;
    PaymentHandler loginfohandler;
    private KafkaUtil kafkaUtil;
    private static final String TOPIC = "paymentInfoResult";

    public void execute(Tuple tuple) {
        String orderInfo = tuple.getString(0);
        System.out.println(orderInfo);
        PaymentInfo order = loginfohandler.getOrdersBean(orderInfo);
        System.err.println("---------------------------------------"+order.toString());
        //store the salesByMerchant infomation into Kafka
        kafkaUtil.send(GsonUtil.in(order),TOPIC);
    }

    @Override
    public void prepare(Map map, TopologyContext topologyContext, org.apache.storm.task.OutputCollector outputCollector) {
        this.collector = collector;
        this.loginfohandler = new PaymentHandler();
        this.kafkaUtil = new KafkaUtil();
    }

    public void declareOutputFields(OutputFieldsDeclarer arg0) {

    }
}