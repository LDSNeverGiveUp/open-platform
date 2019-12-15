package com.zhj.dao;

import com.zhj.domain.SysUserRole;

import java.util.List;

public interface SysUserRoleDAO {
    public Integer insertUserRole(SysUserRole userRole);
    public Integer insertMultUserRole(List<SysUserRole> userRoles);
    public Integer deleteUserRole(Integer userId);
    public List<Integer> queryUserRoles(Integer userId);
}