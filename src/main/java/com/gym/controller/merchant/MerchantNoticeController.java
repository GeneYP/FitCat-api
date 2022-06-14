package com.gym.controller.merchant;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gym.bean.AjaxResult;
import com.gym.entity.FcGymNotice;
import com.gym.service.GymNoticeService;
import com.gym.vo.FcGymNoticeBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/merchant/notice")
@Slf4j
public class MerchantNoticeController {
    @Resource
    GymNoticeService gymNoticeService;

    /**
     * 列表
     *
     * @return
     */
    @GetMapping(value = "/list")
    public AjaxResult<IPage<FcGymNoticeBean>> getPage(int current, int size, HttpServletRequest request) {
        try {
            Page<FcGymNoticeBean> page = new Page<>(current, size);
            return AjaxResult.success(gymNoticeService.userPageVo(page, request), "查询成功");
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
    public AjaxResult update(@RequestBody FcGymNotice fcGymNotice) {
        try {
            gymNoticeService.updateNotice(fcGymNotice);
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
    public AjaxResult save(@RequestBody FcGymNotice fcGymNotice) {
        try {
            gymNoticeService.saveNotice(fcGymNotice);
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
            gymNoticeService.removeById(id);
            return AjaxResult.success(null, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.unsuccess("删除失败：" + e.getMessage());
        }
    }
}
