package com.zhj.service;

import com.github.pagehelper.PageInfo;
import com.zhj.domain.SysRole;
import com.zhj.vo.PageParam;
import com.zhj.vo.SysRoleWithMenus;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface SysRoleService{
    public Set<String> queryRoleByUsername(String username);
    public List<SysRole> querySysRoles();
    public PageInfo<SysRole> querySysRoles(PageParam pageParam);
    public Integer saveRole(SysRoleWithMenus role);
    public SysRoleWithMenus querySysRoleByRoleId(Integer roleId);
    public Map<String,List<Object>> queryRoleCount();
}
