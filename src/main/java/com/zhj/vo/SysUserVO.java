package com.zhj.vo;


import com.zhj.domain.SysUser;

//支持用户登录时，接收请求参数
public class SysUserVO extends SysUser {

    private String captcha;//验证码字符

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

}
