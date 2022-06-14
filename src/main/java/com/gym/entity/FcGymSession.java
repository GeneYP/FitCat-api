package com.gym.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.models.auth.In;
import lombok.Data;

@Data
public class FcGymSession {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer gymId;
    private String name;
    private String tag;
    private String img;
    private Integer level;
}
