package com.gym.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gym.dao.FcSysUserMapper;
import com.gym.entity.FcGymEquip;
import com.gym.entity.FcSysUser;
import com.gym.exception.ApiException;
import com.gym.vo.RegisterBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SysUserService extends ServiceImpl<FcSysUserMapper, FcSysUser> {
    @Resource
    FcSysUserMapper sysUserMapper;

    /**
     * 注册
     *
     * @param registerBean
     */
    public void register(RegisterBean registerBean) {
        if (StringUtils.isAnyBlank(registerBean.getPassword(), registerBean.getUsername())) {
            throw new ApiException("参数错误");
        }
        QueryWrapper<FcSysUser> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(FcSysUser::getUsername, registerBean.getUsername());
        long count = sysUserMapper.selectCount(wrapper);
        if (count > 0) {
            throw new ApiException("用户名已存在");
        }
        FcSysUser fcSysUser = new FcSysUser();
        fcSysUser.setUsername(registerBean.getUsername());
        fcSysUser.setPassword(registerBean.getPassword());
        fcSysUser.setRole(1);
        sysUserMapper.insert(fcSysUser);
    }

    public void saveSysuser(FcSysUser fcSysUser) {
        QueryWrapper<FcSysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", fcSysUser.getUsername());
        long count = count(queryWrapper);
        if (count > 0) {
            throw new ApiException("用户名已存在");
        }
        fcSysUser.setRole(1);
        save(fcSysUser);
    }

    public void updateSysuser(FcSysUser fcSysUser) {
        QueryWrapper<FcSysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", fcSysUser.getUsername());
        queryWrapper.ne("user_id", fcSysUser.getUserId());
        long count = count(queryWrapper);
        if (count > 0) {
            throw new ApiException("用户名已存在");
        }
        fcSysUser.setRole(1);
        updateById(fcSysUser);
    }
}
