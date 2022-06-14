package com.gym.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gym.entity.FcGymActive;
import com.gym.vo.FcGymActiveBean;
import org.apache.ibatis.annotations.Select;

public interface FcGymActiveMapper extends BaseMapper<FcGymActive> {
    @Select("SELECT a.*,g.gym_name AS gymName FROM " +
            "fc_gym_active a, " +
            "fc_gym g " +
            "WHERE a.gym_id = g.gym_id and g.user_id = #{userId}")
    IPage<FcGymActiveBean> selectUserPageVo(Page<FcGymActiveBean> page, String userId);

    @Select("SELECT a.*,g.gym_name AS gymName FROM " +
            "fc_gym_active a, " +
            "fc_gym g " +
            "WHERE a.gym_id = g.gym_id")
    IPage<FcGymActiveBean> selectPageVo(Page<FcGymActiveBean> page);
}
