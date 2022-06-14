package com.gym.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gym.config.BigDecimalSerialize;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class FcGymCard {
    @TableId(value = "card_id", type = IdType.AUTO)
    private Integer cardId;
    private Integer gymId;
    @JsonSerialize(using = BigDecimalSerialize.class)
    private BigDecimal price;
    private Integer stocks;
    private String cardName;
    private Integer status;
}
