package com.haizhi.webdemo.dao;

import com.haizhi.webdemo.entity.UserDo;

import java.util.List;
import java.util.Map;

public interface DemoDao {
    List<UserDo> testMybatis(UserDo user);

    List<UserDo> query(Map<String, Object> hashMap);
}
