package com.gym.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gym.config.BigDecimalSerialize;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class FcGym {
    @TableId(value = "gym_id", type = IdType.AUTO)
    private Integer gymId;
    private Integer userId;
    private String gymName;
    private String province;
    private String city;
    private String area;
    private String address;
    private String remark;
    private String phone;
    private String openTime;
    private String closeTime;
    private String pic;
    private String lng;
    private String lat;
    @JsonSerialize(using = BigDecimalSerialize.class)
    private BigDecimal price;
    @JsonSerialize(using = BigDecimalSerialize.class)
    private BigDecimal stars;
    private Integer status;
}
