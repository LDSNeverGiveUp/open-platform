package com.zhj.dao;

import com.zhj.domain.SysRoleMenu;

import java.util.List;

public interface SysRoleMenuDAO {
    public Integer insertMultRoleMenu(List<SysRoleMenu> rm);
    public List<Integer> queryRoleMenus(Integer roleId);
    /*public Integer insertUserRole(SysUserRole userRole);
    public Integer deleteUserRole(Integer userId);
    public List<Integer> queryUserRoles(Integer userId);*/
}