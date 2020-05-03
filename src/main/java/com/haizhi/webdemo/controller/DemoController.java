package com.haizhi.webdemo.controller;

import com.haizhi.webdemo.entity.CommonResponse;
import com.haizhi.webdemo.entity.UserDo;
import com.haizhi.webdemo.service.DemoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class DemoController {
    @Autowired
    private DemoService demoService;

    @PostMapping("/testMybatis")
    @ApiOperation("测试mybatis")
    public CommonResponse testMybatis(@RequestBody UserDo user) {
        return CommonResponse.success(demoService.testMybatis(user));
    }


    @GetMapping("/testSql")
    @ApiOperation("测试sql")
    public CommonResponse testSql() {
        return CommonResponse.success(demoService.testSql());
    }

}
