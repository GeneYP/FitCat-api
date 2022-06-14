package com.gym.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gym.entity.FcUser;
import com.gym.vo.FcUserBean;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface FcUserMapper extends BaseMapper<FcUser> {
    @Select({"<script>",
            "SELECT g.gym_id AS gym_id, c.id AS cardId, c.expire AS expire, g.gym_name AS gym_name,u.*  FROM ",
            "fc_user u ",
            "LEFT JOIN fc_user_card c " ,
            "on u.id = c.user_id and c.gym_id in (select gym_id from fc_gym where user_id = #{userId}) " ,
            "LEFT JOIN fc_gym g on c.gym_id = g.gym_id and g.user_id = #{userId} " ,
            "WHERE 1=1 " ,
            "<when test='keyword!=null and keyword!=&apos;&apos;'>",
            "AND u.username like CONCAT('%',#{keyword},'%')",
            "</when>",
            "<when test='keyword==&apos;&apos;'>",
            "AND c.expire is not null",
            "</when>",
            "</script>"})
    IPage<FcUserBean> selectUserPageVo(Page<FcUserBean> page, @Param("keyword")String keyword, @Param("userId")String userId);
}
