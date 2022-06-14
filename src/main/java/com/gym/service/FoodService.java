package com.gym.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gym.dao.FcFoodMapper;
import com.gym.entity.FcFood;
import com.gym.entity.FcGym;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService extends ServiceImpl<FcFoodMapper, FcFood> {

    public List<FcFood> getByName(String name) {
        QueryWrapper<FcFood> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", name);
        return list(queryWrapper);
    }
}
