package com.gym.controller.fc;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gym.bean.AjaxResult;
import com.gym.entity.FcUser;
import com.gym.service.AuthService;
import com.gym.service.UserService;
import com.gym.vo.AuthBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/fc/auth")
@Slf4j
public class FcAuthController {
    @Resource
    AuthService authService;
    @Resource
    UserService userService;

    /**
     * 注册
     * @return
     */
    @PostMapping(value = "/register")
    public AjaxResult register(@RequestBody FcUser fcUser) {
        try {
            userService.register(fcUser);
            return AjaxResult.success(null, "注册成功");
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.unsuccess("注册失败：" + e.getMessage());
        }
    }
    /**
     * 认证
     * @param authBean
     * @return
     */
    @PostMapping(value = "/login")
    public AjaxResult<String> fcLogin(@RequestBody @Valid AuthBean authBean) {
        try {
            log.info("fc user login:{}", JSON.toJSON(authBean));
            if(StringUtils.isAnyBlank(authBean.getUsername(), authBean.getPassword())){
                return AjaxResult.unsuccess(null,"用户名或密码不能为空");
            }
            String token = authService.fcUserLogin(authBean);
            return AjaxResult.success(token,"认证成功");
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.unsuccess("认证失败");
        }
    }

    @GetMapping(value = "/logout")
    public AjaxResult<String> fcLogout(HttpServletRequest request) {
        try {
            authService.fcLogout(request);
            return AjaxResult.success(null,"退出成功");
        }catch (Exception e){
            return AjaxResult.unsuccess("退出失败：" + e.getMessage());
        }
    }

    @GetMapping(value = "/info")
    public AjaxResult<JSONObject> getUserInfo(HttpServletRequest request) {
        try {
            log.info("info.....");
            return AjaxResult.success(authService.getUserInfo(request));
        }catch (Exception e){
            return AjaxResult.unsuccess("无用户信息：" + e.getMessage());
        }
    }
}
