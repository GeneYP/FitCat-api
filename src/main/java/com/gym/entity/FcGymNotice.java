package com.gym.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class FcGymNotice {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer gymId;
    private String title;
    private String content;
}
