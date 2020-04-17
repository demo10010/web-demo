package com.haizhi.webdemo.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class KafkaMessageDo {

    @ApiModelProperty(example = "10086")
    private Long id;    //id

    @ApiModelProperty(example = "我的测试消息")
    private String msg; //消息

    private Date sendTime;  //时间戳
}
