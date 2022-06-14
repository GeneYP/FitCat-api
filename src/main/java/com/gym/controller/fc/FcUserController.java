package com.gym.controller.fc;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gym.bean.AjaxResult;
import com.gym.entity.FcSysUser;
import com.gym.entity.FcUser;
import com.gym.entity.FcUserCard;
import com.gym.entity.FcUserCollection;
import com.gym.service.AuthService;
import com.gym.service.UserCardService;
import com.gym.service.UserCollectionService;
import com.gym.service.UserService;
import com.gym.vo.FcUserBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/fc/user")
@Slf4j
public class FcUserController {
    @Resource
    UserService userService;
    @Resource
    UserCardService userCardService;
    @Resource
    UserCollectionService userCollectionService;
    @Resource
    AuthService authService;

    // collection card food plan

    @GetMapping(value = "/card/{userId}")
    public AjaxResult<List<FcUserCard>> getUserCard(@PathVariable Integer userId) {
        try {
            QueryWrapper<FcUserCard> queryWrapper = new QueryWrapper();
            queryWrapper.lambda().eq(FcUserCard::getUserId, userId);
            return AjaxResult.success(userCardService.list(queryWrapper));
        } catch (Exception e) {
            return AjaxResult.unsuccess("获取用户会员卡信息失败：" + e.getMessage());
        }
    }

    @GetMapping(value = "/collection/{userId}")
    public AjaxResult<List<FcUserCollection>> getUserCollection(@PathVariable Integer userId) {
        try {
            QueryWrapper<FcUserCollection> queryWrapper = new QueryWrapper();
            queryWrapper.lambda().eq(FcUserCollection::getUserId, userId);
            return AjaxResult.success(userCollectionService.list(queryWrapper));
        } catch (Exception e) {
            return AjaxResult.unsuccess("获取用户收藏信息失败：" + e.getMessage());
        }
    }

    @GetMapping(value = "/setCollection/{userId}/{gymId}")
    public AjaxResult setUserCollection(@PathVariable Integer gymId, @PathVariable Integer userId) {
        try {
            userCollectionService.saveUserCollection(userId, gymId);
            return AjaxResult.success(null, "添加成功");
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.unsuccess("添加失败：" + e.getMessage());
        }
    }

    @GetMapping(value = "/delCollection/{colId}")
    public AjaxResult delUserCollection(@PathVariable String colId) {
        try {
            userCollectionService.removeById(colId);
            return AjaxResult.success(null, "添加成功");
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.unsuccess("添加失败：" + e.getMessage());
        }
    }

    @GetMapping(value = "/info/{userId}")
    public AjaxResult<FcUser> getUserInfo(@PathVariable Integer userId) {
        try {
            return AjaxResult.success(userService.getById(userId));
        } catch (Exception e) {
            return AjaxResult.unsuccess("获取用户信息失败：" + e.getMessage());
        }
    }

    @GetMapping("/userId")
    public Integer getUserIdByToken(HttpServletRequest request) {
        String token = request.getHeader("token");
        log.info("获得的token为：{}", token);
        String userId = authService.getUserId(request);
        log.info("userId={}", userId);
        return Integer.valueOf(userId);
    }

    /**
     * 更新
     * @return
     */
    @PostMapping(value = "/update")
    public AjaxResult updateUser(@RequestBody FcUser fcUser) {
        try {
            userService.updateFcUser(fcUser);
            return AjaxResult.success(null, "更新成功");
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.unsuccess("更新失败：" + e.getMessage());
        }
    }
}
