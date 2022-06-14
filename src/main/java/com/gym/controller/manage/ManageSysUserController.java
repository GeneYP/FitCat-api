package com.gym.controller.manage;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gym.bean.AjaxResult;
import com.gym.entity.FcSysUser;
import com.gym.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/manage/sysuser")
@Slf4j
public class ManageSysUserController {
    @Resource
    SysUserService sysUserService;

    /**
     * 列表
     * @return
     */
    @GetMapping(value = "/all")
    public AjaxResult<List<FcSysUser>> getAll() {
        try {
            QueryWrapper<FcSysUser> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(FcSysUser::getRole, 1);
            return AjaxResult.success(sysUserService.list(queryWrapper), "查询成功");
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.unsuccess("查询失败：" + e.getMessage());
        }
    }

    /**
     * 列表
     * @return
     */
    @GetMapping(value = "/list")
    public AjaxResult<IPage<FcSysUser>> getPage(int current, int size) {
        try {
            Page<FcSysUser> page = new Page<>(current, size);
            QueryWrapper<FcSysUser> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(FcSysUser::getRole, 1);
            return AjaxResult.success(sysUserService.page(page, queryWrapper), "查询成功");
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.unsuccess("查询失败：" + e.getMessage());
        }
    }

    /**
     * 更新
     * @return
     */
    @PostMapping(value = "/update")
    public AjaxResult update(@RequestBody FcSysUser fcSysUser) {
        try {
            sysUserService.updateSysuser(fcSysUser);
            return AjaxResult.success(null, "更新成功");
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.unsuccess("更新失败：" + e.getMessage());
        }
    }

    /**
     * 添加
     * @return
     */
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody FcSysUser fcSysUser) {
        try {
            sysUserService.saveSysuser(fcSysUser);
            return AjaxResult.success(null, "添加成功");
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.unsuccess("添加失败：" + e.getMessage());
        }
    }

    /**
     * 删除
     * @return
     */
    @PostMapping(value = "/delete/{id}")
    public AjaxResult delete(@PathVariable Integer id) {
        try {
            sysUserService.removeById(id);
            return AjaxResult.success(null, "删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.unsuccess("删除失败：" + e.getMessage());
        }
    }
}
