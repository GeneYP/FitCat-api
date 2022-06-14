package com.gym.controller.fc;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gym.bean.AjaxResult;
import com.gym.entity.FcFood;
import com.gym.service.FoodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/fc/food")
@Slf4j
public class FcFoodController {
    @Resource
    FoodService foodService;

    /**
     * 所有
     *
     * @return
     */
    @GetMapping(value = "/all")
    public AjaxResult<List<FcFood>> getAllFood() {
        try {
            return AjaxResult.success(foodService.list(), "查询成功");
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
    public AjaxResult<IPage<FcFood>> getPage(int current, int size) {
        try {
            Page<FcFood> page = new Page<>(current, size);
            return AjaxResult.success(foodService.page(page), "查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.unsuccess("查询失败：" + e.getMessage());
        }
    }

    /**
     * 所有
     *
     * @return
     */
    @GetMapping(value = "/name/{name}")
    public AjaxResult<List<FcFood>> getFoodByName(@PathVariable String name) {
        try {
            return AjaxResult.success(foodService.getByName(name), "查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.unsuccess("查询失败：" + e.getMessage());
        }
    }
}
