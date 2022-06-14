package com.gym.service;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gym.bean.Constant;
import com.gym.config.JwtPara;
import com.gym.dao.FcSysUserMapper;
import com.gym.dao.FcUserMapper;
import com.gym.entity.FcSysUser;
import com.gym.entity.FcUser;
import com.gym.enums.RoleEnum;
import com.gym.exception.ApiException;
import com.gym.util.RedisUtils;
import com.gym.vo.AuthBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class AuthService {
    @Resource
    private FcSysUserMapper fcSysUserMapper;
    @Resource
    private FcUserMapper fcUserMapper;
    @Resource
    JWTService jwtService;
    @Resource
    private RedisUtils redisUtils;
    @Resource
    private JwtPara jwt;

    /**
     * 用户认证
     *
     * @param authBean
     * @return
     */
    public String fcUserLogin(AuthBean authBean) {
        QueryWrapper<FcUser> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(FcUser::getUsername, authBean.getUsername());
        List<FcUser> users = fcUserMapper.selectList(wrapper);
        if (users == null || users.isEmpty()) {
            throw new ApiException("用户名或密码错误");
        }
        FcUser fcUser = users.get(0);
        if (!authBean.getPassword().equals(fcUser.getPassword())) {
            throw new ApiException("用户名或密码错误");
        }
        String token = jwtService.createToken(fcUser.getId() + "", RoleEnum.USER.name());
        String redisKey = "token:" + RoleEnum.USER.name() + ":" + fcUser.getId();
        redisUtils.set(redisKey, token);
        redisUtils.expire(redisKey, jwt.getExpiresAt(), TimeUnit.DAYS);
        return token;
    }

    /**
     * 退出
     *
     * @param request
     */
    public void fcLogout(HttpServletRequest request) {
        String token = request.getHeader(Constant.TOKEN_HEADER);
        if (StringUtils.isBlank(token)) {
            throw new ApiException("无认证信息");
        }
        String userId = jwtService.getAudience(token);
        String role = jwtService.getRole(token);
        if (StringUtils.isNoneBlank(userId, role)) {
            redisUtils.del("token:" + role + ":" + userId);
        }
    }

    /**
     * 管系统认证
     *
     * @param authBean
     * @return
     */
    public String manageLogin(AuthBean authBean) {
        QueryWrapper<FcSysUser> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(FcSysUser::getUsername, authBean.getUsername());
        List<FcSysUser> sysUsers = fcSysUserMapper.selectList(wrapper);
        if (sysUsers == null || sysUsers.isEmpty()) {
            throw new ApiException("用户名或密码错误");
        }
        FcSysUser fcSysUser = sysUsers.get(0);
        if (!authBean.getPassword().equals(fcSysUser.getPassword())) {
            throw new ApiException("用户名或密码错误");
        }
        RoleEnum roleEnum = fcSysUser.getRole() == 0 ? RoleEnum.MANAGER : RoleEnum.MERCHANT;
        String token = jwtService.createToken(fcSysUser.getUserId() + "", roleEnum.name());
        String redisKey = "token:" + roleEnum.name() + ":" + fcSysUser.getUserId();
        redisUtils.set(redisKey, token);
        redisUtils.expire(redisKey, jwt.getExpiresAt(), TimeUnit.DAYS);
        return token;
    }

    /**
     * 退出
     *
     * @param request
     */
    public void manageLogout(HttpServletRequest request) {
        String token = request.getHeader(Constant.TOKEN_HEADER);
        if (StringUtils.isBlank(token)) {
            throw new ApiException("无认证信息");
        }
        String userId = jwtService.getAudience(token);
        String role = jwtService.getRole(token);
        if (StringUtils.isNoneBlank(userId, role)) {
            redisUtils.del("token:" + role + ":" + userId);
        }
    }

    /**
     * 获取用户信息
     *
     * @param request
     * @return
     */
    public JSONObject getUserInfo(HttpServletRequest request) {
        String token = request.getHeader(Constant.TOKEN_HEADER);
        if (StringUtils.isBlank(token)) {
            throw new ApiException("无认证信息");
        }
        String userId = jwtService.getAudience(token);
        FcSysUser fcSysUser = fcSysUserMapper.selectById(userId);
        String role = jwtService.getRole(token);
        if (StringUtils.isNoneBlank(userId, role)) {
            JSONObject json = new JSONObject();
            json.put("userId", userId);
            json.put("role", role);
            json.put("username", fcSysUser.getUsername());
            return json;
        }
        return null;
    }

    /**
     * 获取当前用户ID
     * @param request
     * @return
     */
    public String getUserId(HttpServletRequest request){
        String token = request.getHeader(Constant.TOKEN_HEADER);
        if (StringUtils.isBlank(token)) {
            throw new ApiException("无认证信息");
        }
        return jwtService.getAudience(token);
    }
}
