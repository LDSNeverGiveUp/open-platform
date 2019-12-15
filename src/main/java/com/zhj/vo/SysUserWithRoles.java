package com.zhj.vo;

import com.zhj.domain.SysUser;

import java.util.List;

//支持用户登录时，接收请求参数
public class SysUserWithRoles extends SysUser {

    private List<Integer> roles;

    public List<Integer> getRoles() {
        return roles;
    }

    public void setRoles(List<Integer> roles) {
        this.roles = roles;
    }
}