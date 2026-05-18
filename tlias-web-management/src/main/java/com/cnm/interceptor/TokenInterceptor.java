package com.cnm.interceptor;

import com.cnm.utils.CurrentHolder;
import com.cnm.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor
{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        //获取到请求路径
        String requestURI=request.getRequestURI();
        //判断是否是登录请求，如果路径中包含/login,说明是登录操作,放行
        if(requestURI.contains("/login"))
        {
            log.info("登录请求，放行");
            return true;
        }
        //获取请求头中的token
        String token=request.getHeader("token");
        //判断token是否存在，如果不存在，说明用户没有登录，返回错误信息（响应401状态码）
        if(token==null||token.isEmpty())
        {
            log.info("令牌为空，响应401");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        //如果token存在，校验令牌，如果校验失败->返回错误信息（响应401状态码）
        try
        {
            Claims claims=JwtUtils.parseToken(token);
            Integer empId=Integer.valueOf(claims.get("id").toString());
            CurrentHolder.setCurrentId(empId);
            log.info("当前登录的员工ID：{},将其存入ThreadLocal",empId);
        } catch (Exception e)
        {
            log.info("令牌非法，响应401");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        //校验通过，放行
        log.info("令牌合法，放行");
        //删除ThreadLocal中的数据
        CurrentHolder.remove();
        return true;
    }
}
