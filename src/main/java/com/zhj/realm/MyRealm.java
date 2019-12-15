package com.zhj.realm;

import com.zhj.domain.SysUser;
import com.zhj.service.SysMenuService;
import com.zhj.service.SysRoleService;
import com.zhj.service.SysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.Set;


public class MyRealm extends AuthorizingRealm{

    private SysUserService userService;
    private SysRoleService roleService;
    private SysMenuService permService;
    // 是否支持token
    public boolean supports(AuthenticationToken token) {
        //return token != null && getAuthenticationTokenClass().isAssignableFrom(token.getClass());
        if( token instanceof UsernamePasswordToken){
            return true;
        }
        return false;
    }

    // 会被securityManager 调用 去查询权限信息
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("real query AuthorizationInfo~~~");
        //获取用户名
        SysUser user = (SysUser) principals.getPrimaryPrincipal();
        //查询 角色和权限
        Set<String> roles = roleService.queryRoleByUsername(user.getUsername());
        Set<String> perms = permService.queryPermByUsername(user.getUsername());
        //将 查询到的角色 roles  和权限 perms 封装
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(roles);
        info.setStringPermissions(perms);
        return info;
    }

    /**
     * // 会被securityManager 调用 去查询身份信息
     * 通过用户名，查询用户信息，并返回
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户名
        String username = (String)token.getPrincipal();
        System.out.println("username:"+username);
        //查询用户身份信息
        // 获取Spring工厂
        SysUser user = userService.querySysUserByUserName(username);//返回查询结果
        // 封装一个AuthenticationInfo
        if(user!=null) {
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user,
                                                                         user.getPassword(),
                                                                         ByteSource.Util.bytes(user.getSalt()),
                                                                         getName());
            return info;
        }
        return null;
    }

    public SysUserService getUserService() {
        return userService;
    }

    public void setUserService(SysUserService userService) {
        this.userService = userService;
    }

    public SysRoleService getRoleService() {
        return roleService;
    }

    public void setRoleService(SysRoleService roleService) {
        this.roleService = roleService;
    }

    public SysMenuService getPermService() {
        return permService;
    }

    public void setPermService(SysMenuService permService) {
        this.permService = permService;
    }
}
