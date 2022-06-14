package com.gym.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gym.bean.Constant;
import com.gym.dao.FcUserCardMapper;
import com.gym.dao.FcUserMapper;
import com.gym.entity.FcUser;
import com.gym.entity.FcUserCard;
import com.gym.exception.ApiException;
import com.gym.vo.FcUserBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Service
public class UserService extends ServiceImpl<FcUserMapper, FcUser> {
    @Resource
    AuthService authService;
    @Resource
    FcUserMapper userMapper;
    @Resource
    FcUserCardMapper userCardMapper;

    /**
     * 注册
     *
     * @param fcUser
     */
    public void register(FcUser fcUser) {
        if (StringUtils.isAnyBlank(fcUser.getPassword(), fcUser.getUsername())) {
            throw new ApiException("参数错误");
        }
        QueryWrapper<FcUser> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(FcUser::getUsername, fcUser.getUsername());
        long count = userMapper.selectCount(wrapper);
        if (count > 0) {
            throw new ApiException("用户名已存在");
        }
        userMapper.insert(fcUser);
    }

    public IPage<FcUserBean> userPageVo(Page<FcUserBean> page, String keyword, HttpServletRequest request) {
        String userId = authService.getUserId(request);
        return userMapper.selectUserPageVo(page, keyword, userId);
    }

    public void saveUser(FcUserBean fcUserBean) {
        //插入会员卡
        FcUserCard fcUserCard = new FcUserCard();
        fcUserCard.setUserId(fcUserBean.getId());
        fcUserCard.setGymId(fcUserBean.getGymId());
        fcUserCard.setExpire(fcUserBean.getExpire());
        userCardMapper.insert(fcUserCard);
    }

    public void updateUser(FcUserBean fcUserBean) {
        FcUserCard fcUserCard = userCardMapper.selectById(fcUserBean.getCardId());
        if(fcUserCard == null){
            throw new ApiException("会员卡不存在");
        }
        //更新会员卡
        fcUserCard.setGymId(fcUserBean.getGymId());
        fcUserCard.setExpire(fcUserBean.getExpire());
        userCardMapper.updateById(fcUserCard);
    }

    public void deleteUser(Integer id) {
        //删除会员卡
        QueryWrapper<FcUserCard> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(FcUserCard::getUserId, id);
        userCardMapper.delete(queryWrapper);
        //删除用户
        removeById(id);
    }

    /**
     * 修改用户
     *
     * @param fcUser
     */
    public void updateFcUser(FcUser fcUser) {
//        if (StringUtils.isAnyBlank(fcUser.getPassword(), fcUser.getUsername())) {
//            throw new ApiException("参数错误");
//        }
        QueryWrapper<FcUser> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(FcUser::getUsername, fcUser.getUsername());
        long count = userMapper.selectCount(wrapper);
        if (count == 0) {
            throw new ApiException("用户名不存在");
        }
        userMapper.update(fcUser, wrapper);
    }
}
