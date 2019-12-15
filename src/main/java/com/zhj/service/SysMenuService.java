package com.zhj.service;

import com.zhj.domain.SysMenu;

import java.util.List;
import java.util.Set;

public interface SysMenuService {
    public Set<String> queryPermByUsername(String username);
    public List<SysMenu> queryMenuByUsername(String username);
    public List<SysMenu> queryAllMenus();
}
