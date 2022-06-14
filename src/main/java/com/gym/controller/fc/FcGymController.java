package com.gym.controller.fc;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gym.bean.AjaxResult;
import com.gym.entity.FcGym;
import com.gym.service.GymService;
import com.gym.service.GymTagService;
import com.gym.util.RecUserBasedUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/fc/gym")
@Slf4j
public class FcGymController {
    @Resource
    GymService gymService;
    @Resource
    GymTagService gymTagService;

    /**
     * 详情
     *
     * @return
     */
    @GetMapping(value = "/detail/{gymId}")
    public AjaxResult<FcGym> getDetail(@PathVariable String gymId) {
        try {
            return AjaxResult.success(gymService.getById(gymId), "查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.unsuccess("查询失败：" + e.getMessage());
        }
    }

    /**
     * 详情
     *
     * @return
     */
    @GetMapping(value = "/tag/{gymId}")
    public AjaxResult<FcGym> getGymTag(@PathVariable Integer gymId) {
        try {
            return AjaxResult.success(gymTagService.getByGymId(gymId), "查询成功");
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
    @GetMapping(value = "/list/{type}/{name}")
    public AjaxResult<List<FcGym>> getList(@PathVariable String type, @PathVariable String name) {
        try {
            return AjaxResult.success(gymService.getListByType(type, name), "查询成功");
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
    @GetMapping(value = "/rec/{userId}")
    public AjaxResult<List<FcGym>> getRecList(@PathVariable Integer userId) {
        try {
            List<RecommendedItem> recommendations = RecUserBasedUtil.recGymType(userId);
            String type = "tag";
            String name = "HIIT";
            for (RecommendedItem recommendation : recommendations) {
//                System.out.println(recommendation.getItemID());
                if (recommendation.getItemID() == 1) {
                    name = "瑜伽";
                } else if (recommendation.getItemID() == 2) {
                    name = "自由搏击";
                } else if (recommendation.getItemID() == 3) {
                    name = "力量举";
                } else if (recommendation.getItemID() == 4) {
                    name = "游泳";
                } else if (recommendation.getItemID() == 5) {
                    name = "单车";
                }
            }
            return AjaxResult.success(gymService.getListByType(type, name), "查询成功");
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
    @GetMapping(value = "/search/{gymName}")
    public AjaxResult<List<FcGym>> getListByName(@PathVariable String gymName) {
        try {
            return AjaxResult.success(gymService.getListByName(gymName), "查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.unsuccess("查询失败：" + e.getMessage());
        }
    }

}
