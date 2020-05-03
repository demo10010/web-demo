package com.haizhi.webdemo.service;


import com.haizhi.webdemo.entity.UserDo;

import java.util.List;

public interface DemoService {

    List<UserDo> testMybatis(UserDo user);

    List<UserDo> testSql();
}
