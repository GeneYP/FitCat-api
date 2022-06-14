package com.gym.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gym.config.BigDecimalSerialize;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class FcUser {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String username;
    private String password;
    @JsonSerialize(using = BigDecimalSerialize.class)
    private BigDecimal height;
    @JsonSerialize(using = BigDecimalSerialize.class)
    private BigDecimal weight;
    private Integer gender;
    private String pic;
    private Integer age;
    private String hobby;
}
