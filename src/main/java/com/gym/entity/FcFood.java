package com.gym.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gym.config.BigDecimalSerialize;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class FcFood {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String name;
    private String img;
    @JsonSerialize(using = BigDecimalSerialize.class)
    private BigDecimal cal;
    @JsonSerialize(using = BigDecimalSerialize.class)
    private BigDecimal fat;
    @JsonSerialize(using = BigDecimalSerialize.class)
    private BigDecimal sugar;
    @JsonSerialize(using = BigDecimalSerialize.class)
    private BigDecimal protein;
}
