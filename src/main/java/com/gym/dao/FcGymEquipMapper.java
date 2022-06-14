package com.gym.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gym.entity.FcGymEquip;
import com.gym.vo.FcGymEquipBean;
import org.apache.ibatis.annotations.Select;

public interface FcGymEquipMapper extends BaseMapper<FcGymEquip> {
    @Select("SELECT e.*,g.gym_name AS gymName FROM " +
            "fc_gym_equip e, " +
            "fc_gym g " +
            "WHERE e.gym_id = g.gym_id and g.user_id = #{userId}")
    IPage<FcGymEquipBean> selectUserPageVo(Page<FcGymEquipBean> page, String userId);
}
