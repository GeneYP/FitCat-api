package com.gym.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gym.dao.FcGymActiveMapper;
import com.gym.entity.FcGymActive;
import com.gym.exception.ApiException;
import com.gym.vo.FcGymActiveBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Service
public class GymActiveService extends ServiceImpl<FcGymActiveMapper, FcGymActive> {
    @Resource
    AuthService authService;
    @Resource
    FcGymActiveMapper fcGymActiveMapper;

    public IPage<FcGymActiveBean> pageVo(Page<FcGymActiveBean> page ) {
        return fcGymActiveMapper.selectPageVo(page);
    }

    public IPage<FcGymActiveBean> userPageVo(Page<FcGymActiveBean> page, HttpServletRequest request) {
        String userId = authService.getUserId(request);
        return fcGymActiveMapper.selectUserPageVo(page, userId);
    }

    public void saveActive(FcGymActive fcGymActive) {
        QueryWrapper<FcGymActive> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", fcGymActive.getName());
        queryWrapper.eq("gym_id", fcGymActive.getGymId());
        long count = count(queryWrapper);
        if (count > 0) {
            throw new ApiException("活动已存在");
        }
        fcGymActive.setStatus(0);
        save(fcGymActive);
    }

    public void updateActive(FcGymActive fcGymActive) {
        QueryWrapper<FcGymActive> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", fcGymActive.getName());
        queryWrapper.eq("gym_id", fcGymActive.getGymId());
        queryWrapper.ne("id", fcGymActive.getId());
        long count = count(queryWrapper);
        if (count > 0) {
            throw new ApiException("活动已存在");
        }
        updateById(fcGymActive);
    }

    public void deleteActive(Integer id) {
        removeById(id);
    }

    public void updateStatus(Integer id, Integer status) {
        FcGymActive active = fcGymActiveMapper.selectById(id);
        if(active==null){
            throw new ApiException("活动不存在");
        }
        active.setStatus(status);
        updateById(active);
    }
}
