package com.gym.vo;

import com.gym.entity.FcGymActive;
import com.gym.entity.FcGymSession;
import lombok.Data;

@Data
public class FcGymActiveBean extends FcGymActive {
    private String gymName;
}
