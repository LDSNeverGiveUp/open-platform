package com.zhj.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhj.dao.SysUserDAO;
import com.zhj.dao.SysUserRoleDAO;
import com.zhj.domain.SysUser;
import com.zhj.domain.SysUserRole;
import com.zhj.service.SysUserService;
import com.zhj.vo.SysUserWithRoles;
import com.zhj.vo.PageParam;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserDAO userDAO;
    @Autowired
    private SysUserRoleDAO userRoleDAO;

    // 登录中使用，shiro realm调用
    public SysUser querySysUserByUserName(String username) {
        return userDAO.querySysUserByUserName(username);
    }

    // 查询用户信息，分页，用户列表需要
    public PageInfo<SysUser> querySysUsers(PageParam pageParam){
        PageHelper.offsetPage(pageParam.getOffset(),pageParam.getLimit());
        List<SysUser> sysUsers = userDAO.querySysUsers();
        return new PageInfo<SysUser>(sysUsers);
    }

    // 查询用户信息和角色id，在更新用户时 回显数据需要
    public SysUserWithRoles querySysUserByUserId(Integer userId) {
        SysUser sysUser = userDAO.querySysUserByUserId(userId);
        List<Integer> roles = userRoleDAO.queryUserRoles(userId);
        SysUserWithRoles user = new SysUserWithRoles();
        BeanUtils.copyProperties(sysUser,user);
        user.setRoles(roles);
        return user;
    }

    // 增加用户
    @Transactional
    public void insertUserWithRole(SysUserWithRoles user) {
        // 设置创建时间
        user.setCreateTime(new Date());
        //处理 密码
        String salt = UUID.randomUUID().toString();
        //加密 密码，并转成 base64格式
        String pwd = new Sha256Hash(user.getPassword(), salt, 1024).toBase64();
        // 覆盖用户的明文密码
        user.setPassword(pwd);
        // 设置盐
        user.setSalt(salt);
        //增加用户
        userDAO.insertSysUser((SysUser)user);
        // 为用户指定角色
        List<SysUserRole> urs = new ArrayList<SysUserRole>();
        for (Integer roleId : user.getRoles()) {
            urs.add(new SysUserRole(user.getUserId(),roleId));
        }
        userRoleDAO.insertMultUserRole(urs);
    }

    //更新用户信息 ( 包含其所持有的角色信息 )
    @Transactional
    public void updateUserWithRole(SysUserWithRoles user) {
        // 更新用户信息，不包括修改密码
        userDAO.updateSysUser((SysUser)user);
        // 更新用户的角色信息，先删除用户的所有角色，然后重新赋予角色
        // 先删除用户的所有角色
        userRoleDAO.deleteUserRole(user.getUserId());
        // 为用户指定角色
        List<SysUserRole> urs = new ArrayList<SysUserRole>();
        for (Integer roleId : user.getRoles()) {
            urs.add(new SysUserRole(user.getUserId(),roleId));
        }
        userRoleDAO.insertMultUserRole(urs);
    }

    @Transactional
    public void deleteUser(Integer userId){
        userDAO.deleteSysUser(userId);
        userRoleDAO.deleteUserRole(userId);
    }
}