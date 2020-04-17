package com.haizhi.webdemo.controller;

import com.haizhi.webdemo.entity.KafkaMessageDo;
import com.haizhi.webdemo.producer.MyKafkaProducer;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class KafkaController {

    @Autowired
    private MyKafkaProducer myKafkaProducer;

    @PostMapping("/test")
    @ApiOperation("测试kafka")
    public void testSql(@RequestBody KafkaMessageDo msg) {
        myKafkaProducer.send(msg);
    }
}
