package com.haizhi.webdemo.service;

import com.haizhi.webdemo.entity.UserInfo;

public interface UserInfoService {
    /**通过username查找用户信息;*/
    UserInfo findByUsername(String username);
}
