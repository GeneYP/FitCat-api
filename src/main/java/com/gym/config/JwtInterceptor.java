package com.gym.config;

import com.gym.service.JWTService;
import com.gym.util.LoginUtil;
import com.gym.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class JwtInterceptor implements HandlerInterceptor {
    @Resource
    private JWTService jwtService;
    @Resource
    private RedisUtils redisUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) {
        String token = request.getHeader("authorization");
        log.info("web.jwt authorization:{}",token);
//        if (!(obj instanceof HandlerMethod)) {
//            return true;
//        }
        if (token == null || token.isEmpty()) {
            LoginUtil.loginRequired(response);
            return false;
        }
        String userId;
        try {
            userId = jwtService.getAudience(token);
            String role = jwtService.getRole(token);
            String tokenKey = "token:"+ role.toUpperCase() +":" + userId;
            if(!redisUtils.hasKey(tokenKey) || !token.equals(redisUtils.get(tokenKey))){
                LoginUtil.loginRequired(response);
                return false;
            }
        }catch (Exception e){
            log.error("jwtService 获取token失败{}",e.getMessage());
            LoginUtil.loginRequired(response);
            return false;
        }
        // 验证 token 是否过期
        boolean b = jwtService.expireToken(token, userId);
        if (b) {
            LoginUtil.loginRequired(response);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }


}
