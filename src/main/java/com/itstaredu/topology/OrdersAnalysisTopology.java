package com.itstaredu.topology;

import com.itstaredu.bolt.BrowserAnalyseBolt;
import com.itstaredu.bolt.PaymentAnalyseBolt;
import com.itstaredu.bolt.RuleInfoAnalyseBolt;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.kafka.*;
import org.apache.storm.spout.SchemeAsMultiScheme;
import org.apache.storm.topology.TopologyBuilder;
import java.util.UUID;
/**
 * 具体的orderAnalyse处理类
 */
public class OrdersAnalysisTopology {
    // 声明browser的topic
    private static String browserLog = "browserLog";
    // 声明payment类的topic
    private static String paymentInfo = "paymentInfo";
    // 声明ruleinfo的topic
    private static String ruleInfo = "ruleInfo";
    // 声明的topic
    private static String zkRoot = "/testqwe";


    public static void main(String[] args) throws AuthorizationException {
        // 声明zookeeper的连接地址
        BrokerHosts hosts = new ZkHosts("node2:2181,node11:2181,node8:2181");
        // 声明builder变量
        TopologyBuilder builder = new TopologyBuilder();
        // 声明browserlog的kafkaSpout 配置
        SpoutConfig browserLogspoutConfig = new SpoutConfig(hosts, browserLog, zkRoot, UUID.randomUUID().toString());
        // 声明paymentInfo的kafkaSpout 配置
        SpoutConfig PaymentInfoSpoutConfig = new SpoutConfig(hosts, paymentInfo, zkRoot, UUID.randomUUID().toString());
        // 声明ruleInfo的kafkaSpout 配置
        SpoutConfig RuleInfoSpoutConfig = new SpoutConfig(hosts, ruleInfo, zkRoot, UUID.randomUUID().toString());
        // 配置browserSpout的scheme配置
        browserLogspoutConfig.scheme = new SchemeAsMultiScheme(new StringScheme());
        // 配置paymentInfo的scheme配置
        PaymentInfoSpoutConfig.scheme = new SchemeAsMultiScheme(new StringScheme());
        // 配置ruleInfo的scheme配置
        RuleInfoSpoutConfig.scheme = new SchemeAsMultiScheme(new StringScheme());
        // 创建KafkaSpout
        KafkaSpout browserSpout = new KafkaSpout(browserLogspoutConfig);
        KafkaSpout paymentSpout = new KafkaSpout(PaymentInfoSpoutConfig);
        KafkaSpout ruleinfoSpout = new KafkaSpout(RuleInfoSpoutConfig);
        // 构造Spout信息
        builder.setSpout("browserSpout", browserSpout);
        builder.setSpout("paymentSpout",paymentSpout);
        builder.setSpout("ruleinfoSpout",ruleinfoSpout);
        // 构造分析bolt
        builder.setBolt("browserBolt", new BrowserAnalyseBolt(), 2).shuffleGrouping("browserSpout");
        builder.setBolt("paymentBolt", new PaymentAnalyseBolt(), 2).shuffleGrouping("paymentSpout");
        builder.setBolt("ruleinfoBolt", new RuleInfoAnalyseBolt(), 2).shuffleGrouping("ruleinfoSpout");
        Config conf = new Config();
        // debug模式
        conf.setDebug(true);
        // 设置在本地提交还是远程提交
        if (args != null && args.length > 0) {
            // 远程提交模式
            conf.setNumWorkers(1);
            try {
                // 设置对应的topology名称
                StormSubmitter.submitTopologyWithProgressBar(args[0], conf, builder.createTopology());
            } catch (AlreadyAliveException | InvalidTopologyException e) {
                e.printStackTrace();
            }
        } else {
            // 本地提交模式
            conf.setMaxSpoutPending(3);
            LocalCluster cluster = new LocalCluster();
            cluster.submitTopology("ordersAnalysis", conf, builder.createTopology());
        }

    }
}
