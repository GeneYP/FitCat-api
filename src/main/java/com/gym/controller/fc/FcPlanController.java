package com.gym.controller.fc;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gym.bean.AjaxResult;
import com.gym.entity.FcPlan;
import com.gym.service.PlanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/fc/plan")
@Slf4j
public class FcPlanController {
    @Resource
    PlanService planService;

    /**
     * 所有
     *
     * @return
     */
    @GetMapping(value = "/all")
    public AjaxResult<List<FcPlan>> getAllPlan() {
        try {
            return AjaxResult.success(planService.list(), "查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.unsuccess("查询失败：" + e.getMessage());
        }
    }

    /**
     * 分页列表
     *
     * @return
     */
    @GetMapping(value = "/list")
    public AjaxResult<IPage<FcPlan>> getPage(int current, int size) {
        try {
            Page<FcPlan> page = new Page<>(current, size);
            return AjaxResult.success(planService.page(page), "查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.unsuccess("查询失败：" + e.getMessage());
        }
    }
}
