package com.zhj.service;

import com.github.pagehelper.PageInfo;
import com.zhj.domain.SysUser;
import com.zhj.vo.SysUserWithRoles;
import com.zhj.vo.PageParam;

public interface SysUserService {
    public SysUser querySysUserByUserName(String username);
    public PageInfo<SysUser> querySysUsers(PageParam pageParam);
    public SysUserWithRoles querySysUserByUserId(Integer userId);
    public void insertUserWithRole(SysUserWithRoles user);
    public void updateUserWithRole(SysUserWithRoles user);
    public void deleteUser(Integer userId);
}

