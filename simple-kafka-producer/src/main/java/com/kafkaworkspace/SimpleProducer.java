package com.kafkaworkspace;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class SimpleProducer {
    private static final String TOPIC_NAME = "test";
    private static final String BOOTSTRAP_SERVERS = "13.124.221.140:9092";

    public static void main(String[] args) {
        Properties configs = new Properties();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<>(configs);

        for (int i = 0; i < 10; i++) {
            String data = "This is record" + i;
            //ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_NAME, data);
            //ProducerRecor의 파라미터로 토픽명과, 데이터 사이에 key 값을 세팅할 수 있다.
            ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_NAME, String.valueOf(i), data);

            try {
                producer.send(record);
                System.out.println("Send to " + TOPIC_NAME + " | data : " + data);
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
