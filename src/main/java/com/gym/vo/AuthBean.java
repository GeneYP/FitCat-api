package com.gym.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "登录参数")
public class AuthBean {
    @ApiModelProperty(value = "用户名", name = "username", required = true, example = "admin")
    private String username;
    @ApiModelProperty(value = "密码", name = "password", required = true, example = "123456")
    private String password;
}
