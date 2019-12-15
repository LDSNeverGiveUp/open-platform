package com.zhj.service.impl;

import com.zhj.dao.SysMenuDAO;
import com.zhj.domain.SysMenu;
import com.zhj.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SysMenuServiceImpl implements SysMenuService {
    @Autowired
    private SysMenuDAO menuDAO;

    // 查询用户权限，为shiro权限认证准备，realm中会来调用
    // 首页显示时，也需要加载用户所有的权限，以决定要显示那些按钮
    public Set<String> queryPermByUsername(String username) {
        Set<String> perms = menuDAO.queryPermsByUserName(username);
        Set<String> perms2 = new HashSet<String>();
        System.out.println("username:"+username+" perms:"+perms.size());
        for (String s : perms) {
            String[] split = s.split(",");
            perms2.addAll(Arrays.asList(split));
        }
        return perms2;
    }

    // 查询所有菜单，首页显示时，菜单栏需要此项数据
    public List<SysMenu> queryMenuByUsername(String username){
        List<SysMenu> sysMenus = menuDAO.queryUserFirstLevelMenus(username);
        for (SysMenu sysMenu : sysMenus) {
            List<SysMenu> sysMenus1 = menuDAO.queryUserSecondLevelMenusByUserName(username, sysMenu.getMenuId());
            sysMenu.setList(sysMenus1);
        }
        return sysMenus;
    }




    //查询所有菜单，供新增和修改 角色时使用
    public List<SysMenu> queryAllMenus() {
        return menuDAO.queryAll();
    }
}