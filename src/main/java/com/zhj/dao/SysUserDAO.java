package com.zhj.dao;

import com.zhj.domain.SysUser;

import java.util.List;

public interface SysUserDAO {
    public SysUser querySysUserByUserName(String username);
    public List<SysUser> querySysUsers();
    public SysUser querySysUserByUserId(Integer userId);
    public Integer insertSysUser(SysUser user);
    public Integer updateSysUser(SysUser user);
    public Integer updateSysUser();
    public Integer deleteSysUser(Integer userId);
}