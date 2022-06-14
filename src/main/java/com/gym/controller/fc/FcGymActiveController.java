package com.gym.controller.fc;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gym.bean.AjaxResult;
import com.gym.entity.FcGymActive;
import com.gym.entity.FcGymSession;
import com.gym.service.GymActiveService;
import com.gym.service.GymSessionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/fc/active")
@Slf4j
public class FcGymActiveController {
    @Resource
    GymActiveService gymActiveService;

    /**
     * 所有活动
     *
     * @return
     */
    @GetMapping(value = "/all")
    public AjaxResult<List<FcGymActive>> getActiveList() {
        try {
            QueryWrapper<FcGymActive> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("status", 1);
            queryWrapper.orderByDesc("position");
            return AjaxResult.success(gymActiveService.list(queryWrapper), "查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.unsuccess("查询失败：" + e.getMessage());
        }
    }

}
