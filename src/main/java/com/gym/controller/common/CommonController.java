package com.gym.controller.common;

import com.gym.bean.AjaxResult;
import com.gym.bean.Constant;
import com.gym.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/common")
@Slf4j
public class CommonController {
    @Resource
    CommonService commonService;

    /**
     * 上传
     *
     * @return
     */
    @PostMapping(value = "/upload")
    public AjaxResult<String> upload(@RequestParam(value = "file") MultipartFile file) {
        try {
            return AjaxResult.success(commonService.upload(file), "上传成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.unsuccess("上传失败：" + e.getMessage());
        }
    }

    /**
     * 查看图片
     * @param filename
     * @param response
     * @throws Exception
     */
//    @GetMapping(value = "/image/{filename}", produces = MediaType.IMAGE_JPEG_VALUE)
//    public void getImage(@PathVariable String filename, HttpServletResponse response) throws Exception {
//        String realName = filename.replace("_", ".");
//        Path path = Paths.get(Constant.UPLOAD + realName);
//        try {
//            byte[] data = Files.readAllBytes(path);
//            response.setContentType(MediaType.IMAGE_JPEG_VALUE);
//            OutputStream out = response.getOutputStream();
//            out.write(data);
//            out.flush();
//            关闭响应输出流
//            out.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

}
