package com.gym.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gym.dao.FcGymEquipMapper;
import com.gym.dao.FcGymTagMapper;
import com.gym.entity.FcGym;
import com.gym.entity.FcGymEquip;
import com.gym.entity.FcGymTag;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GymTagService extends ServiceImpl<FcGymTagMapper, FcGymTag> {

    public List<FcGymTag> getByGymId(Integer gymId) {
        QueryWrapper<FcGymTag> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("gym_id", gymId);
        return list(queryWrapper);
    }
}
