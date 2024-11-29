package com.tlias.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.tlias.pojo.Result;
import com.tlias.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    // 目标资源方法运行前运行，返回true；放行，返回false，不放行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1、获取请求url
        String url = request.getRequestURL().toString();

        // 2、判断请求url中是否包含login，如果包含，说明是登录操作，放行
        // 在WebConfig里已处理，可不写
        if(url.contains("login")) {
            return true;
        }

        // 3、获取请求头中的令牌（token）
        String jwt = request.getHeader("token");

        // 4、判断令牌是否存在，如果不存在，返回错误结果（未登录）
        if(!StringUtils.hasLength(jwt)) {
            Result error = Result.error("NOT_LOGIN");

            // 手动转换 对象 --> json   阿里巴巴fastJSON
            String notLogin = JSONObject.toJSONString(error);
            response.getWriter().write(notLogin);
            return false;
        }

        // 5、解析token，如果解析失败，返回错误结果（未登录）
        try {
            JwtUtils.parseJWT(jwt);  // 解析不报错，即有效
        } catch (Exception e) {
            e.printStackTrace();
            Result error = Result.error("NOT_LOGIN");

            // 手动转换 对象 --> json   阿里巴巴fastJSON
            String notLogin = JSONObject.toJSONString(error);
            response.getWriter().write(notLogin);
            return false;
        }

        // 6、放行
        return true;
    }

    // 目标资源方法运行后运行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    // 视图渲染完毕后运行，最后运行
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
