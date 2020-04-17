package com.haizhi.webdemo.producer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.haizhi.webdemo.constant.CommonConstants;
import com.haizhi.webdemo.entity.KafkaMessageDo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class MyKafkaProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    //发送消息方法
    public void send(KafkaMessageDo message) {
        message.setSendTime(new Date());
        log.info("MyKafkaProducer message = {}", CommonConstants.KAFKA_GSON.toJson(message));
        //无key
        kafkaTemplate.send(CommonConstants.TEST_TOPIC, CommonConstants.KAFKA_GSON.toJson(message));
        //有key
        kafkaTemplate.send(CommonConstants.TEST_TOPIC_KEY, "my-key", "with my-key : " + message.getMsg());
    }

}
