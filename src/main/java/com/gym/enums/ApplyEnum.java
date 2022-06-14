package com.gym.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 审批状态 0:申请中 1:通过 2:拒绝 3:取消
 */
@Getter
@AllArgsConstructor
public enum ApplyEnum {
    INIT(0, "申请中"),
    OK(1, "通过"),
    DENY(2, "拒绝"),
    CANCEL(3, "取消"),
    TIMEOUT(4, "过期");

    private final Integer code;
    private final String description;

    public static ApplyEnum find(Integer code) {
        for (ApplyEnum value : ApplyEnum.values()) {
            if (code.equals(value.getCode())) {
                return value;
            }
        }
        return null;
    }

}
