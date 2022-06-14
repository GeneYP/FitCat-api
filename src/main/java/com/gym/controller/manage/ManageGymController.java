package com.gym.controller.manage;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gym.bean.AjaxResult;
import com.gym.entity.FcGym;
import com.gym.service.GymService;
import com.gym.vo.FcGymBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/manage/gym")
@Slf4j
public class ManageGymController {
    @Resource
    GymService gymService;

    /**
     * 列表
     *
     * @return
     */
    @GetMapping(value = "/list")
    public AjaxResult<IPage<FcGymBean>> getPage(int current, int size) {
        try {
            Page<FcGymBean> page = new Page<>(current, size);
            return AjaxResult.success(gymService.pageVo(page), "查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.unsuccess("查询失败：" + e.getMessage());
        }
    }

    /**
     * 更新
     *
     * @return
     */
    @PostMapping(value = "/update")
    public AjaxResult update(@RequestBody FcGym fcGym) {
        try {
            gymService.updateById(fcGym);
            return AjaxResult.success(null, "更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.unsuccess("更新失败：" + e.getMessage());
        }
    }

    /**
     * 添加
     *
     * @return
     */
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody FcGym fcGym) {
        try {
            gymService.save(fcGym);
            return AjaxResult.success(null, "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.unsuccess("添加失败：" + e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @return
     */
    @PostMapping(value = "/delete/{id}")
    public AjaxResult delete(@PathVariable Integer id) {
        try {
            gymService.removeById(id);
            return AjaxResult.success(null, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.unsuccess("删除失败：" + e.getMessage());
        }
    }
}
