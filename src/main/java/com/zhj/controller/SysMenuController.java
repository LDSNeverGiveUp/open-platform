package com.zhj.controller;

import com.zhj.domain.SysMenu;
import com.zhj.domain.SysUser;
import com.zhj.service.SysMenuService;
import com.zhj.utils.R;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin
public class SysMenuController {

    @Autowired
    private SysMenuService menuService;

    // 查询当前用户的所有权限和对应菜单 首页加载时会访问。支持左侧菜单栏 和 正文中按钮的显示
    @RequestMapping("/sys/menu/user")
    public R queryMenuAndPerms(){
        Subject subject = SecurityUtils.getSubject();
        SysUser user = (SysUser)SecurityUtils.getSubject().getPrincipal();
        String username = user.getUsername();
        Set<String> perms = menuService.queryPermByUsername(username);
        List<SysMenu> sysMenus = menuService.queryMenuByUsername(username);
        return R.ok().put("menuList",sysMenus).put("permissions",perms);
    }

    // 查询所有菜单 支持角色的添加和修改时 选择菜单
    @RequestMapping("/sys/menu/select")
    public R queryMenus(){
        List<SysMenu> sysMenus = menuService.queryAllMenus();
        return R.ok().put("menuList",sysMenus);
    }
}