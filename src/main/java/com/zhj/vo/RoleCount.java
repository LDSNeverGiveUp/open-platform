package com.zhj.vo;

/**
 * 接收每种角色的各性别的人数
 */
public class RoleCount {
    private String roleName;
    private Integer male;
    private Integer female;

    @Override
    public String toString() {
        return "RoleCount{" +
                "roleName='" + roleName + '\'' +
                ", male=" + male +
                ", female=" + female +
                '}';
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getMale() {
        return male;
    }

    public void setMale(Integer male) {
        this.male = male;
    }

    public Integer getFemale() {
        return female;
    }

    public void setFemale(Integer female) {
        this.female = female;
    }
}
