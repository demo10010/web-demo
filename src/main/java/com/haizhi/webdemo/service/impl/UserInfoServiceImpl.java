package com.haizhi.webdemo.service.impl;

import com.haizhi.webdemo.dao.UserInfoDao;
import com.haizhi.webdemo.entity.UserInfo;
import com.haizhi.webdemo.service.UserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Resource
    private UserInfoDao userInfoDao;
    @Override
    public UserInfo findByUsername(String username) {
        System.out.println("UserInfoServiceImpl.findByUsername()");
        return userInfoDao.findByUsername(username);
    }
}
