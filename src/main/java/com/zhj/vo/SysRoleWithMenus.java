package com.zhj.vo;

import com.zhj.domain.SysRole;

import java.util.List;

//支持用户登录时，接收请求参数
public class SysRoleWithMenus extends SysRole {

    private List<Integer> menus;

    public List<Integer> getMenus() {
        return menus;
    }

    public void setMenus(List<Integer> menus) {
        this.menus = menus;
    }

    @Override
    public String toString() {
        return "SysRoleWithMenus{" +
                "menus=" + menus +
                '}'+super.toString();
    }
}