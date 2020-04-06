package com.haizhi.webdemo.service.impl;

import com.google.common.collect.Maps;
import com.haizhi.webdemo.dao.UserDao;
import com.haizhi.webdemo.entity.User;
import com.haizhi.webdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void testMybatis(User user) {
//        User user = User.builder().id(1).name("name1").age(20).build();
        Integer insertUser = userDao.insertUser(user);
        System.out.println(insertUser);
        user.setAge(25);
        Integer updateUser = userDao.updateUser(user);
        System.out.println(updateUser);

        User byId = userDao.findById(user.getId());
        System.out.println(byId);

        Map<String, Object> map = Maps.newHashMap();
        map.put("name",user.getName());
        User byMap = userDao.findByMap(map);
        System.out.println(byMap);

        User byBean = userDao.findByBean(user);
        System.out.println(byBean);

        Integer deleteUser = userDao.deleteUser(user.getId());
        System.out.println(deleteUser);
    }
}
