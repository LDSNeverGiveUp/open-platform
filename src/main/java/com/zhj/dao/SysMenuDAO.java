package com.zhj.dao;

import com.zhj.domain.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface SysMenuDAO {
    public Set<String> queryPermsByUserName(String username);
    public List<SysMenu> queryAll();
    public List<SysMenu> queryUserFirstLevelMenus(String username);
    public List<SysMenu> queryUserSecondLevelMenusByUserName(@Param("username") String username, @Param("parentId") Long parentId);
}