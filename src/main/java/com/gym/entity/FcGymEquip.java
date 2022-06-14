package com.gym.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class FcGymEquip {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer gymId;
    private String name;
}
