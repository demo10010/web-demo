package com.haizhi.webdemo.consumer;

import com.haizhi.webdemo.constant.CommonConstants;
import com.haizhi.webdemo.entity.KafkaMessageDo;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class MyKafkaConsumer {

    @KafkaListener(topics = {CommonConstants.TEST_TOPIC})
    public void listen(ConsumerRecord<String, String> record) {
        Optional<String> msg = Optional.ofNullable(record.value());
        if (msg.isPresent()) {
            String message = msg.get();
            log.info("MyKafkaConsumer raw key: ", record.key());//
            log.info(" message body : {}", message);
            KafkaMessageDo kafkaMessageDo = CommonConstants.KAFKA_GSON.fromJson(message, KafkaMessageDo.class);
            log.info(" kafkaMessageDo.msg : {}", kafkaMessageDo.getMsg());
        }
    }

    @KafkaListener(topics = {CommonConstants.TEST_TOPIC_KEY})
    public void withKeyListen(ConsumerRecord<String, String> record) {
        Optional<String> msg = Optional.ofNullable(record.value());
        if (msg.isPresent()) {
            String message = msg.get();
            log.info("with key MyKafkaConsumer raw key: ", record.key());
            log.info("with key message body : {}", message);
        }
    }
}
