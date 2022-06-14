package com.gym.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gym.bean.Constant;
import com.gym.dao.FcGymMapper;
import com.gym.entity.FcGym;
import com.gym.exception.ApiException;
import com.gym.vo.FcGymBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class GymService extends ServiceImpl<FcGymMapper, FcGym> {
    @Resource
    AuthService authService;
    @Resource
    JWTService jwtService;
    @Resource
    FcGymMapper gymMapper;

    /**
     * 根据类型获取列表
     *
     * @param type
     * @param name
     * @return
     */
    public List<FcGym> getListByType(String type, String name) {
        QueryWrapper<FcGym> queryWrapper = new QueryWrapper<>();
        if ("city".equals(type)) {
            queryWrapper.like("city", name);
        } else {
            queryWrapper.inSql("gym_id", "select gym_id from fc_gym_tag where title like '%" + name + "%'");
        }
        return list(queryWrapper);
    }

    /**
     * 分页查询
     * @param page
     * @return
     */
    public IPage<FcGymBean> pageVo(Page<FcGymBean> page) {
        return gymMapper.selectPageVo(page);
    }

    /**
     * 用户所有健身房
     * @param request
     * @return
     */
    public List<FcGym> getAllUserGym(HttpServletRequest request) {
        String userId = authService.getUserId(request);
        QueryWrapper<FcGym> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        return list(queryWrapper);
    }

    /**
     * 分页查询用户健身房
     * @param page
     * @return
     */
    public IPage<FcGymBean> userPageVo(Page<FcGymBean> page,HttpServletRequest request) {
        String userId = authService.getUserId(request);
        return gymMapper.selectUserPageVo(page, userId);
    }

    /**
     * 查询名字
     * @param gymName
     * @return
     */
    public List<FcGym> getListByName(String gymName) {
        QueryWrapper<FcGym> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("gym_name", gymName);
        return list(queryWrapper);
    }

}
