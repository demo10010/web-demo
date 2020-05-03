package com.haizhi.webdemo.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDo {
    @ApiModelProperty(example = "1")
    private int id;

    @ApiModelProperty(example = "name1")
    private String name;

    @ApiModelProperty(value = "20")
    private int age;
}
