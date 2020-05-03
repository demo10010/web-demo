package com.haizhi.webdemo.dao;

import com.haizhi.webdemo.entity.UserDo;

public interface UserDao {

    UserDo getUserByName(String name);
}
