package com.gym.bean;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 操作消息提醒
 *
 */
@Data
public class AjaxResult<T>
{
    @ApiModelProperty(value = "响应状态")
    private int code;

    private T data;

    @ApiModelProperty(value = "描述")
    private String desc;

    @ApiModelProperty(value = "业务是否成功")
    private Boolean success;


    /**
     * 初始化一个新创建的 AjaxResult 对象
     *
     * @param code 状态码
     * @param data 数据对象
     */
    public AjaxResult(int code, Boolean success, T data, String desc)
    {
        this.code=code;
        this.success=success;
        this.data=data;
        this.desc=desc;
    }

    /**
     * 返回成功消息
     *
     * @param data 数据对象
     * @return 成功消息
     */
    public static AjaxResult success(Object data)
    {
        return new AjaxResult(200,true,data,null);
    }
    /**
     * 返回成功消息
     *
     * @param data 数据对象
     * @return 成功消息
     */
    public static AjaxResult success(Object data,String desc)
    {
        return new AjaxResult(200,true,data,desc);
    }

    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static AjaxResult unsuccess(String desc)
    {
        return new AjaxResult(500,false,null,desc);
    }


    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static AjaxResult unsuccess(String data, String desc)
    {
        return new AjaxResult(200,false,data,desc);
    }


    /**
     * 无权限
     *
     * @return 成功消息
     */
    public static AjaxResult notAuth()
    {
        return new AjaxResult(401,false,null, "没有权限");
    }

    /**
     * 返回错误消息
     *
     * @return 警告消息
     */
    public static AjaxResult error(String msg)
    {
        return new AjaxResult(500,false,null, msg);
    }

    /**
     * 返回错误消息
     *
     * @return 警告消息
     */
    public static AjaxResult notfound()
    {
        return new AjaxResult(404,false,null, "接口未找到");
    }

    /**
     * 返回错误消息
     *
     * @return 警告消息
     */
    public static AjaxResult notReadable()
    {
        return new AjaxResult(400,false,null, "参数解析失败");
    }

}