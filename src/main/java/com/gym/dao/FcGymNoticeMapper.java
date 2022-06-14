package com.gym.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gym.entity.FcGymNotice;
import com.gym.vo.FcGymNoticeBean;
import org.apache.ibatis.annotations.Select;

public interface FcGymNoticeMapper extends BaseMapper<FcGymNotice> {
    @Select("SELECT n.*,g.gym_name AS gymName FROM " +
            "fc_gym_notice n, " +
            "fc_gym g " +
            "WHERE n.gym_id = g.gym_id and g.user_id = #{userId}")
    IPage<FcGymNoticeBean> selectUserPageVo(Page<FcGymNoticeBean> page, String userId);
}
