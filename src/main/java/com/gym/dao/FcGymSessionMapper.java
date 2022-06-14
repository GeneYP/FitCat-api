package com.gym.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gym.entity.FcGymSession;
import com.gym.vo.FcGymSessionBean;
import org.apache.ibatis.annotations.Select;

public interface FcGymSessionMapper extends BaseMapper<FcGymSession> {
    @Select("SELECT s.*,g.gym_name AS gymName FROM " +
            "fc_gym_session s, " +
            "fc_gym g " +
            "WHERE s.gym_id = g.gym_id and g.user_id = #{userId}")
    IPage<FcGymSessionBean> selectUserPageVo(Page<FcGymSessionBean> page, String userId);
}
