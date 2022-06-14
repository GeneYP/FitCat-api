package com.gym.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gym.dao.FcGymSessionMapper;
import com.gym.entity.FcGymSession;
import com.gym.exception.ApiException;
import com.gym.vo.FcGymSessionBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Service
public class GymSessionService extends ServiceImpl<FcGymSessionMapper, FcGymSession> {
    @Resource
    AuthService authService;
    @Resource
    FcGymSessionMapper gymSessionMapper;

    public IPage<FcGymSessionBean> userPageVo(Page<FcGymSessionBean> page, HttpServletRequest request) {
        String userId = authService.getUserId(request);
        return gymSessionMapper.selectUserPageVo(page, userId);
    }

    public void saveSession(FcGymSession fcGymSession) {
        QueryWrapper<FcGymSession> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", fcGymSession.getName());
        queryWrapper.eq("gym_id", fcGymSession.getGymId());
        long count = count(queryWrapper);
        if (count > 0) {
            throw new ApiException("课程已存在");
        }
        save(fcGymSession);
    }

    public void updateSession(FcGymSession fcGymSession) {
        QueryWrapper<FcGymSession> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", fcGymSession.getName());
        queryWrapper.eq("gym_id", fcGymSession.getGymId());
        queryWrapper.ne("id", fcGymSession.getId());
        long count = count(queryWrapper);
        if (count > 0) {
            throw new ApiException("课程已存在");
        }
        updateById(fcGymSession);
    }

    public void deleteSession(Integer id) {
        removeById(id);
    }
}
