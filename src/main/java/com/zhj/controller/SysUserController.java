package com.zhj.controller;

import com.github.pagehelper.PageInfo;
import com.zhj.domain.SysUser;
import com.zhj.service.SysUserService;
import com.zhj.utils.R;
import com.zhj.vo.SysUserWithRoles;
import com.zhj.vo.PageParam;
import com.zhj.vo.SysUserVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:8082")
public class SysUserController {

    @Autowired
    private SysUserService userService;

    // 登录，身份认证
    @RequestMapping("/sys/login")
    public R login(@RequestBody SysUserVO user){
        // 验证码校验
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        // 登录
        SecurityUtils.getSubject().login(token);
        return R.ok();
    }

    // 查询当前用户信息，支持 “欢迎，xxx”
    @RequestMapping("/sys/user/info")
    public R queryOneUser(){
        Subject subject = SecurityUtils.getSubject();
        SysUser user = (SysUser)SecurityUtils.getSubject().getPrincipal();
        String username = user.getUsername();
        System.out.println("username:"+username+"--");
        SysUser sysUser = userService.querySysUserByUserName(username);
        return R.ok().put("user",sysUser);
    }

    // 查询某个用户信息，支持 更新
    @RequestMapping("/sys/user/info/{userId}")
    public R queryOneUser(@PathVariable Integer userId){
        SysUser sysUser = userService.querySysUserByUserId(userId);
        return R.ok().put("user",sysUser);
    }
    // 查询所有用户信息，注意要分页，支持用户列表展示
    @RequestMapping("/sys/user/list")
    public R queryUsers(PageParam pageParam){
        PageInfo<SysUser> pageInfo = userService.querySysUsers(pageParam);
        return R.ok().put("rows",pageInfo.getList()).put("total",pageInfo.getTotal());
    }

    // 增加用户
    @RequestMapping("/sys/user/save")
    public R createUser(@RequestBody SysUserWithRoles user){
        userService.insertUserWithRole(user);
        return R.ok();
    }

    // 更新用户
    @RequestMapping("/sys/user/update")
    public R updateUser(@RequestBody SysUserWithRoles user){
        userService.updateUserWithRole(user);
        return R.ok();
    }
    // 删除用户
    @RequestMapping("/sys/user/del/{userId}")
    public R deleteUser(@PathVariable Integer userId){
        userService.deleteUser(userId);
        return R.ok();
    }

    // 登出
    @RequestMapping("/logout")
    public R logout(){
        SecurityUtils.getSubject().logout();
        return R.ok();
    }
}