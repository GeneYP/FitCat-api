package com.gym.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gym.dao.FcGymEquipMapper;
import com.gym.entity.FcGymEquip;
import com.gym.exception.ApiException;
import com.gym.vo.FcGymEquipBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Service
public class GymEquipService extends ServiceImpl<FcGymEquipMapper, FcGymEquip> {
    @Resource
    AuthService authService;
    @Resource
    FcGymEquipMapper gymEquipMapper;

    public IPage<FcGymEquipBean> userPageVo(Page<FcGymEquipBean> page, HttpServletRequest request) {
        String userId = authService.getUserId(request);
        return gymEquipMapper.selectUserPageVo(page, userId);
    }

    public void saveEquip(FcGymEquip fcGymEquip) {
        QueryWrapper<FcGymEquip> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", fcGymEquip.getName());
        queryWrapper.eq("gym_id", fcGymEquip.getGymId());
        long count = count(queryWrapper);
        if (count > 0) {
            throw new ApiException("器械已存在");
        }
        save(fcGymEquip);
    }

    public void updateEquip(FcGymEquip fcGymEquip) {
        QueryWrapper<FcGymEquip> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", fcGymEquip.getName());
        queryWrapper.eq("gym_id", fcGymEquip.getGymId());
        queryWrapper.ne("id", fcGymEquip.getId());
        long count = count(queryWrapper);
        if (count > 0) {
            throw new ApiException("器械已存在");
        }
        updateById(fcGymEquip);
    }
}
