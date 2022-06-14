package com.gym.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class FcPlan {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String url;
    private String img;
    private String title;
}
