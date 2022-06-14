package com.gym.vo;

import com.gym.entity.FcUser;
import lombok.Data;

import java.util.Date;

@Data
public class FcUserBean extends FcUser {
    private String gymName;
    private Integer gymId;
    private Integer cardId;
    private Date expire;
}
