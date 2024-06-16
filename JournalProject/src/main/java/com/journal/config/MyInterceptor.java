package com.journal.config;

import com.journal.pojo.basicClass.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // 在处理请求之前进行拦截操作
        // 检查用户会话是否有效，例如检查登录状态等
        User userSession = (User)request.getSession().getAttribute("user");
        if (userSession != null) {
            // 会话存在，则放行请求继续执行后续操作（即执行被拦截方法）
            return true;

        } else {
            // 无效会话，则重定向到登录页面或其他处理逻辑
            response.sendRedirect("/page/Login.html");
            return false;   // 返回 false 表示停止当前请求的执行
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 在请求处理之后、视图渲染之前执行的逻辑
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 在整个请求完成之后执行的逻辑，包括视图渲染完成后
    }
}
