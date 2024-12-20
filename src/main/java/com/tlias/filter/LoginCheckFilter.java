package com.tlias.filter;

import com.alibaba.fastjson.JSONObject;
import com.tlias.pojo.Result;
import com.tlias.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StringUtils;

import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        // 1、获取请求url
        String url = req.getRequestURL().toString();

        // 2、判断请求url中是否包含login，如果包含，说明是登录操作，放行
        if(url.contains("login")) {
            chain.doFilter(request, response);
            return;
        }

        // 3、获取请求头中的令牌（token）
        String jwt = req.getHeader("token");

        // 4、判断令牌是否存在，如果不存在，返回错误结果（未登录）
        if(!StringUtils.hasLength(jwt)) {
            Result error = Result.error("NOT_LOGIN");

            // 手动转换 对象 --> json   阿里巴巴fastJSON
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return;
        }

        // 5、解析token，如果解析失败，返回错误结果（未登录）
        try {
            JwtUtils.parseJWT(jwt);  // 解析不报错，即有效
        } catch (Exception e) {
            e.printStackTrace();
            Result error = Result.error("NOT_LOGIN");

            // 手动转换 对象 --> json   阿里巴巴fastJSON
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return;
        }

        // 6、放行
        chain.doFilter(request, response);
    }
}
