package com.itstaredu.utils;

import com.itstaredu.entity.RuleInfo;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import java.util.Collections;
import java.util.Properties;
/**
 * kafkaUtil工具类，负责发送消息和接受消息
 */
public class KafkaUtil {
    private final String TOPIC = "test";
    private Producer<String, byte[]> producer;
    private KafkaConsumer<String, byte[]> consumer;
    // KafkaUtil的方法构造，String 的序列化和反序列化
    public KafkaUtil() {
        // KafkaProducer配置
        Properties props = new Properties();
        props.put("bootstrap.servers", "node5:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        // KafkaConsumer配置
        Properties props1 = new Properties();
        props1.put("bootstrap.servers", "node5:9092");
        props1.put("group.id", "test");
        props1.put("enable.auto.commit", "true");
        props1.put("auto.commit.interval.ms", "1000");
        props1.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props1.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        producer = new KafkaProducer<>(props);
        consumer = new KafkaConsumer<>(props1);
    }

    /**
     * 消息发送
     * @param json 发送的Json数据
     */
    public void send(String json) {
        producer.send(new ProducerRecord(TOPIC, json));
    }

    /**
     * 消息按topic发送数据
     * @param json json类型数据
     * @param topic 对应的topic类型
     */
    public void send(String json, String topic) {
        producer.send(new ProducerRecord(topic, json));
    }

    public static void main(String[] args) {
        KafkaUtil kafkaUtil = new KafkaUtil();
        kafkaUtil.poll();
    }

    /**
     * 消息消费
     */
    private void poll() {
        consumer.subscribe(Collections.singletonList(TOPIC));
        while (true) {
            ConsumerRecords<String, byte[]> records = consumer.poll(100);
            for (ConsumerRecord<String, byte[]> record : records) {
                RuleInfo ruleInfo = (RuleInfo) KryoUtil.out(record.value(), new RuleInfo());
                System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), ruleInfo.getIsCashonDelivery());
            }
        }
    }

    /**
     * 消息按topic消费
     * @param topic
     */
    private void poll(String topic) {
        consumer.subscribe(Collections.singletonList(topic));
        while (true) {
            ConsumerRecords<String, byte[]> records = consumer.poll(100);
            for (ConsumerRecord<String, byte[]> record : records) {
                RuleInfo ruleInfo = (RuleInfo) KryoUtil.out(record.value(), new RuleInfo());
                System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), ruleInfo.getIsCashonDelivery());
            }
        }
    }

    /**
     * 关闭producer
     */
    public void close() {
        producer.close();
    }
}
