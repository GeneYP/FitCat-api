package com.gym.controller.fc;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gym.bean.AjaxResult;
import com.gym.entity.FcGymEquip;
import com.gym.service.GymEquipService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/fc/gym/equip")
@Slf4j
public class FcGymEquipController {
    @Resource
    GymEquipService gymEquipService;

    /**
     * 器械列表
     *
     * @return
     */
    @GetMapping(value = "/{gymId}")
    public AjaxResult<List<FcGymEquip>> getEquipList(@PathVariable String gymId) {
        try {
            QueryWrapper<FcGymEquip> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("gym_id", gymId);
            return AjaxResult.success(gymEquipService.list(queryWrapper), "查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.unsuccess("查询失败：" + e.getMessage());
        }
    }

}
