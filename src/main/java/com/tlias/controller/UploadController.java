package com.tlias.controller;

import com.tlias.pojo.Result;
import com.tlias.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {
    @Autowired
    private AliOSSUtils aliOSSUtils;

    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws Exception {
        /**
         * 本地存储
         */
        // 获取原始文件名
        // String originalFilename = image.getOriginalFilename();

        // 构造唯一的文件名（不能重复） -- uuid 通用唯一识别码
        // int index = originalFilename.lastIndexOf(".");
        // String extname = originalFilename.substring(index);
        // String newFileName = UUID.randomUUID().toString() + extname;

        // 将文件存储在服务器的磁盘目录中 E:\image
        // image.transferTo(new File("E:\\image\\" + newFileName));
        // return Result.success();

        /**
         * OSS存储
         */
        String url = aliOSSUtils.upload(image);
        return Result.success(url);
    }
}
