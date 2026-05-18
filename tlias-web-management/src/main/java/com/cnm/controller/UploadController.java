package com.cnm.controller;

import com.cnm.pojo.Result;
import com.cnm.pojo.UploadData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController
{
    String uploadPath="D://images/";
    @PostMapping("upload")
    public Result upload(String name, Integer age, MultipartFile file) throws Exception
    {
        UploadData uploadData=new UploadData(name,age,file);
        log.info("接受参数，{}", uploadData);
        //获取原始文件名
        String originalFilename= file.getOriginalFilename();
        String Filename=UUID.randomUUID().toString()+originalFilename.substring(originalFilename.lastIndexOf("."));
        //保存文件
        file.transferTo(new File(uploadPath+ Filename));
        return Result.success();
    }
}
