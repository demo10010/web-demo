package com.haizhi.webdemo.service.impl;

import com.google.common.collect.Maps;
import com.haizhi.webdemo.dao.DemoDao;
import com.haizhi.webdemo.entity.UserDo;
import com.haizhi.webdemo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private DemoDao demoDao;

    @Override
    public List<UserDo> testMybatis(UserDo user) {
        return demoDao.testMybatis(user);
    }

    @Override
    public List<UserDo> testSql() {
        Map<String, Object> hashMap = Maps.newHashMap();
        hashMap.put("name","123");
        return demoDao.query(hashMap);
    }

}
