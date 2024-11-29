package com.tlias.controller;

import com.tlias.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {
    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws Exception {
        // 获取接收到的文件内容的输入流 getInputStream()
        // 获取原始文件名
        String originalFilename = image.getOriginalFilename();

        // 构造唯一的文件名（不能重复） -- uuid 通用唯一识别码
        int index = originalFilename.lastIndexOf(".");
        String extname = originalFilename.substring(index);
        String newFileName = UUID.randomUUID().toString() + extname;

        // 将文件存储在服务器的磁盘目录中 E:\image
        image.transferTo(new File("E:\\image\\" + newFileName));
        return Result.success();
    }
}
