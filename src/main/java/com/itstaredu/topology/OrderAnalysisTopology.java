package com.itstaredu.topology;

import com.itstaredu.bolt.BrowserAnalyseBolt;
import kafka.server.KafkaApis;
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
 * Created by Administrator on 2017-12-01.
 */
public class OrderAnalysisTopology {
    //  声明browserLog的topicName
    private static String browserLog = "browserLog";
    //  声明payment类的topic
    private static String paymentInfo = "paymentInfo";
    //  声明ruleinfo的topicname
    private static String ruleInfo = "ruleInfo";
    // 声明zkRoot
    private  static String zkRoot = "/test";

    public static void main(String[] args) {
        // 声明zookeeper的连接地址
        BrokerHosts hosts = new ZkHosts("node2:2181,node11:2181,node8:2181");
        // 声明builder变量
        TopologyBuilder builder = new TopologyBuilder();
        // 声明browserlog 的kafkaspout 配置
        SpoutConfig browserLogSpoutConfig = new SpoutConfig(hosts,browserLog,zkRoot, UUID.randomUUID().toString());
        SpoutConfig paymentLogSpoutConfig = new SpoutConfig(hosts,browserLog,zkRoot,UUID.randomUUID().toString());
        SpoutConfig ruleInfoSpoutConfig = new SpoutConfig(hosts,ruleInfo,zkRoot,UUID.randomUUID().toString());
        // 声明browserlog paymentInfo ruleInfo 的 Scheme 配置
        browserLogSpoutConfig.scheme= new SchemeAsMultiScheme(new StringScheme());
        paymentLogSpoutConfig.scheme = new SchemeAsMultiScheme(new StringScheme());
        ruleInfoSpoutConfig.scheme = new SchemeAsMultiScheme(new StringScheme());
        // 创建KafkaSpout
        KafkaSpout browserSpout = new KafkaSpout(browserLogSpoutConfig);
        KafkaSpout paymentSpout = new KafkaSpout(paymentLogSpoutConfig);
        KafkaSpout ruleInfoSpout = new KafkaSpout(ruleInfoSpoutConfig);
        // 构造Spout信息,从kafka获得数据源
        builder.setSpout("browserLogSpout",browserSpout);
        builder.setSpout("paymentSpout",paymentSpout);
        builder.setSpout("ruleInfoSpout",ruleInfoSpout);
        //构造分析bolt，对spout进行分析
        builder.setBolt("browserBolt",new BrowserAnalyseBolt(),parallelism_hint:2).shuffleGrouping("browserSpout");
        builder.setBolt("paymentBolt",new BrowserAnalyseBolt(),parallelism_hint:2).shuffleGrouping("paymentSpout");
        builder.setBolt("ruleInfoBolt",new BrowserAnalyseBolt(),parallelism_hint:2).shuffleGrouping("ruleInfoSpout");
        Config conf = new Config();
        // debug 模式
        conf.setDebug(true);
        // 判断本地还是远程提交
        if(args!=null && args.length >0){
            // 远程对应的topology对象
            try {
                StormSubmitter.submitTopologyWithProgressBar(args[0],conf,builder.createTopology());
            } catch (AlreadyAliveException e) {
                e.printStackTrace();
            } catch (InvalidTopologyException e) {
                e.printStackTrace();
            } catch (AuthorizationException e) {
                e.printStackTrace();
            }
        }else {
            // 设置本地提交模式
            conf.setMaxSpoutPending(3);
            LocalCluster cluster = new LocalCluster();
            cluster.submitTopology("orderAnalysis",conf,builder.createTopology());
        }
    }
}
