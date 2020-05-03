package com.haizhi.webdemo.dao;

import lombok.Data;

@Data
public class SysUser {
    private String id;
    private String userName;
    private String password;
    /**
     * 用户对应的角色集合
     */
//    private Set<Role> roles;
}
