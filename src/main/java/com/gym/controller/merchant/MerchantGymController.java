package com.gym.controller.merchant;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gym.bean.AjaxResult;
import com.gym.entity.FcGym;
import com.gym.service.GymService;
import com.gym.vo.FcGymBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/merchant/gym")
@Slf4j
public class MerchantGymController {
    @Resource
    GymService gymService;

    /**
     * 所有
     *
     * @return
     */
    @GetMapping(value = "/all")
    public AjaxResult<List<FcGym>> getAllGym(HttpServletRequest request) {
        try {
            return AjaxResult.success(gymService.getAllUserGym(request), "查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.unsuccess("查询失败：" + e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @return
     */
    @GetMapping(value = "/list")
    public AjaxResult<IPage<FcGymBean>> getPage(int current, int size,HttpServletRequest request) {
        try {
            Page<FcGymBean> page = new Page<>(current, size);
            return AjaxResult.success(gymService.userPageVo(page, request), "查询成功");
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

    /**
     * 删除
     *
     * @return
     */
    @GetMapping(value = "/search/{name}")
    public AjaxResult search(@PathVariable String name) {
        try {
            return AjaxResult.success(gymService.getListByName(name), "查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.unsuccess("删除失败：" + e.getMessage());
        }
    }
}
