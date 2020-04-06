package com.haizhi.webdemo.controller;

import com.haizhi.webdemo.entity.User;
import com.haizhi.webdemo.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/testMybatis")
    @ApiOperation("测试mybatis")
    public void testMybatis(@RequestBody User user) {
        userService.testMybatis(user);
        return;
    }

}
