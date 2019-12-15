package com.zhj.controller;

import com.github.pagehelper.PageInfo;
import com.zhj.domain.SysRole;
import com.zhj.service.SysRoleService;
import com.zhj.utils.R;
import com.zhj.vo.PageParam;
import com.zhj.vo.SysRoleWithMenus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class SysRoleController {

    @Autowired
    private SysRoleService roleService;

    // 查询所有角色，用于前端页面 增加和更新时，显示所有角色复选框
    @GetMapping("/sys/roles")
    public R findAllRoles(){
        List<SysRole> sysRoles = roleService.querySysRoles();
        return R.ok().put("roles",sysRoles);
    }

    // 查询所有用户信息，注意要分页，支持用户列表展示
    @RequestMapping("/sys/role/list")
    public R queryRoles(PageParam pageParam){
        PageInfo<SysRole> pageInfo = roleService.querySysRoles(pageParam);
        return R.ok().put("rows",pageInfo.getList()).put("total",pageInfo.getTotal());
    }

    @RequestMapping("/sys/role/save")
    public R saveRole(@RequestBody SysRoleWithMenus roleWithMenus){
        System.out.println(roleWithMenus);
        roleService.saveRole(roleWithMenus);
        return R.ok();
    }

    @RequestMapping("/sys/role/info/{roleId}")
    public R queryOneUser(@PathVariable Integer roleId){
        SysRoleWithMenus rm = roleService.querySysRoleByRoleId(roleId);
        return R.ok().put("role",rm);
    }
    @RequestMapping("/sys/role/update")
    public R updateRole(@RequestBody SysRoleWithMenus roleWithMenus){
        System.out.println("update");
        System.out.println(roleWithMenus);
        return R.ok();
    }

    // 查询各种角色的男女员工数量
    @RequestMapping("/sys/role/count")
    @ResponseBody
    public R queryRoleCount(){
        Map<String, List<Object>> roleCount = roleService.queryRoleCount();
        return R.ok().put("data",roleCount);
    }

}