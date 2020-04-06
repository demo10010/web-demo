package com.haizhi.webdemo.dao;

import com.haizhi.webdemo.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface UserDao {

    Integer insertUser(User user);

    Integer updateUser(User user);

    User findById(int id);

    User findByMap(Map<String, Object> map);

    User findByBean(User user);

    Integer deleteUser(int id);
}
