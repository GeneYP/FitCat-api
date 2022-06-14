package com.gym.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gym.entity.FcGym;
import com.gym.vo.FcGymBean;
import org.apache.ibatis.annotations.Select;

public interface FcGymMapper extends BaseMapper<FcGym> {

    @Select("SELECT g.*,u.username AS username FROM " +
            "fc_gym g " +
            "LEFT JOIN fc_sys_user u ON g.user_id = u.user_id ")
    IPage<FcGymBean> selectPageVo(Page<FcGymBean> page);

    @Select("SELECT g.*,u.username AS username FROM " +
            "fc_gym g " +
            "LEFT JOIN fc_sys_user u ON g.user_id = u.user_id " +
            "WHERE g.user_id = #{userId}")
    IPage<FcGymBean> selectUserPageVo(Page<FcGymBean> page, String userId);
}
