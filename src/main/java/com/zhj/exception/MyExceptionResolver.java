package com.zhj.exception;

import com.alibaba.fastjson.JSON;
import com.zhj.utils.R;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class MyExceptionResolver implements HandlerExceptionResolver{
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) {
        //response.setCharacterEncoding("utf-8");
        response.setHeader("Content-Type","application/json;charset=utf-8");
        if(e instanceof UnknownAccountException){
            try {
                response.getWriter().print(JSON.toJSONString(R.error("用户名或密码错误")));
                response.getWriter().close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }else if(e instanceof IncorrectCredentialsException){
            try {
                response.getWriter().print(JSON.toJSONString(R.error("用户名或密码错误")));
                response.getWriter().close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }else if(e instanceof AuthorizationException){
            try {
                response.getWriter().print(JSON.toJSONString(R.error("权限不足")));
                response.getWriter().close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }
}
