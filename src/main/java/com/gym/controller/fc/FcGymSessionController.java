package com.gym.controller.fc;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gym.bean.AjaxResult;
import com.gym.entity.FcGymSession;
import com.gym.service.GymSessionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/fc/gym/session")
@Slf4j
public class FcGymSessionController {
    @Resource
    GymSessionService gymSessionService;

    /**
     * 团课列表
     *
     * @return
     */
    @GetMapping(value = "/{gymId}")
    public AjaxResult<List<FcGymSession>> getSessionList(@PathVariable String gymId) {
        try {
            QueryWrapper<FcGymSession> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("gym_id", gymId);
            return AjaxResult.success(gymSessionService.list(queryWrapper), "查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.unsuccess("查询失败：" + e.getMessage());
        }
    }

}
