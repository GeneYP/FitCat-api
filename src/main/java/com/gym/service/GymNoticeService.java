package com.gym.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gym.dao.FcGymNoticeMapper;
import com.gym.entity.FcGymNotice;
import com.gym.exception.ApiException;
import com.gym.vo.FcGymNoticeBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Service
public class GymNoticeService extends ServiceImpl<FcGymNoticeMapper, FcGymNotice> {
    @Resource
    AuthService authService;
    @Resource
    FcGymNoticeMapper gymNoticeMapper;

    public IPage<FcGymNoticeBean> userPageVo(Page<FcGymNoticeBean> page, HttpServletRequest request) {
        String userId = authService.getUserId(request);
        return gymNoticeMapper.selectUserPageVo(page, userId);
    }

    public void updateNotice(FcGymNotice fcGymNotice) {
        QueryWrapper<FcGymNotice> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", fcGymNotice.getTitle());
        queryWrapper.eq("gym_id", fcGymNotice.getGymId());
        queryWrapper.ne("id", fcGymNotice.getId());
        long count = count(queryWrapper);
        if (count > 0) {
            throw new ApiException("通知已存在");
        }
        updateById(fcGymNotice);
    }

    public void saveNotice(FcGymNotice fcGymNotice) {
        QueryWrapper<FcGymNotice> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", fcGymNotice.getTitle());
        queryWrapper.eq("gym_id", fcGymNotice.getGymId());
        long count = count(queryWrapper);
        if (count > 0) {
            throw new ApiException("通知已存在");
        }
        save(fcGymNotice);
    }
}
