package com.gym.service;

import com.gym.bean.Constant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class CommonService {
    @Resource
    QiniuOssService qiniuOssService;

    public String upload(MultipartFile file) throws IOException {
//        String originalFilename = file.getOriginalFilename();
//        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
//        String uuid = UUID.randomUUID().toString().replace("-", "");
//        String fileName = uuid;
//        if (StringUtils.isNotBlank(suffix)) {
//            fileName = uuid + suffix;
//        }
//        File imageFile = new File(Constant.UPLOAD, fileName);
//        file.transferTo(imageFile);
//        String url = Constant.BASE_URL + "/api/common/image/" + imageFile.getName().replace(".", "_");
        return qiniuOssService.uploadOss(file.getInputStream());
    }
}
