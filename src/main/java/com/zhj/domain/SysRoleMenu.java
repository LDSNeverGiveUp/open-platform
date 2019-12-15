package com.zhj.domain;

public class SysRoleMenu {
    private Integer id;
    private Integer menuId;
    private Integer roleId;

    public SysRoleMenu(){}

    public SysRoleMenu(Integer roleId ,Integer menuId) {
        this.menuId = menuId;
        this.roleId = roleId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer userId) {
        this.menuId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "SysUserRole{" +
                "id=" + id +
                ", menuId=" + menuId +
                ", roleId=" + roleId +
                '}';
    }
}
