package com.zhj.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhj.dao.SysRoleDAO;
import com.zhj.dao.SysRoleMenuDAO;
import com.zhj.domain.SysRole;
import com.zhj.domain.SysRoleMenu;
import com.zhj.service.SysRoleService;
import com.zhj.vo.PageParam;
import com.zhj.vo.RoleCount;
import com.zhj.vo.SysRoleWithMenus;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    private SysRoleDAO roleDAO;
    @Autowired
    private SysRoleMenuDAO roleMenuDAO;
    @Override
    // 为shiro 做角色校验准备，Realm中会调用
    public Set<String> queryRoleByUsername(String username) {
        return roleDAO.querySysRolesByUserName(username);
    }

    // 查询所有角色，前端页面做用户 添加和更新时，要显示所有角色
    public List<SysRole> querySysRoles(){
        return roleDAO.querySysRoles();
    }

    // 查询所有角色，分页
    public PageInfo<SysRole> querySysRoles(PageParam pageParam) {
        PageHelper.offsetPage(pageParam.getOffset(),pageParam.getLimit());
        List<SysRole> sysRoles = roleDAO.querySysRoles();
        return new PageInfo<SysRole>(sysRoles);
    }

    // 添加角色
    @Transactional
    public Integer saveRole(SysRoleWithMenus rm) {
        rm.setCreateTime(new Date());
        // 增加角色
        Integer count = roleDAO.insertRole(rm);
        // 增加角色的所有菜单
        List<SysRoleMenu> rms = new ArrayList<SysRoleMenu>();
        for (Integer menuId : rm.getMenus()) {
            rms.add(new SysRoleMenu(rm.getRoleId(),menuId));
        }
        roleMenuDAO.insertMultRoleMenu(rms);
        return count;
    }

    @Override
    public SysRoleWithMenus querySysRoleByRoleId(Integer roleId) {
        SysRole sysRole = roleDAO.querySysRoleByRoleId(roleId);
        List<Integer> menuIds = roleMenuDAO.queryRoleMenus(roleId);
        SysRoleWithMenus rm = new SysRoleWithMenus();
        BeanUtils.copyProperties(sysRole,rm);
        rm.setMenus(menuIds);
        return rm;
    }

    public Map<String,List<Object>> queryRoleCount(){
        List<RoleCount> roleCounts = roleDAO.queryRoleCount();
        //[{xx,1,3},{xx,2,4}]
        Map<String,List<Object>> data = new HashMap();
        data.put("x",new ArrayList());
        data.put("male",new ArrayList());
        data.put("female",new ArrayList());
        for (RoleCount roleCount : roleCounts) {
            data.get("x").add(roleCount.getRoleName());
            data.get("male").add(roleCount.getMale());
            data.get("female").add(roleCount.getFemale());
        }
        return data;
    }
}
