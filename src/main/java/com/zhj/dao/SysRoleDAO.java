package com.zhj.dao;

import com.zhj.domain.SysRole;
import com.zhj.vo.RoleCount;

import java.util.List;
import java.util.Set;

public interface SysRoleDAO {
    public Set<String> querySysRolesByUserName(String username);
    public SysRole querySysRoleByRoleId(Integer roleId);
    public List<SysRole> querySysRoles();
    public Integer insertRole(SysRole role);
    public List<RoleCount> queryRoleCount();
}