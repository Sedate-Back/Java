package com.itheima.reggie.controller;

import com.itheima.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/common")
@Slf4j
public class CommonController {
    @Value("${reg" +
            "gie.path}")
    private String basePath;

    @PostMapping("/upload")
    public R<String> upload(MultipartFile file){
        // file是一个临时文件 需要转存到指定位置
        log.info(file.toString());

        // 原始文件名
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

        // 使用UUID生成新文件名
        String fileName = UUID.randomUUID().toString() + suffix; // xxxxx.jpg

        // 创建目录对象
        File dir = new File(basePath);
        // 判断
        if(!dir.exists()){
            dir.mkdirs();
        }

        try {
            file.transferTo(new File(basePath + fileName));
        }catch (IOException e){
            e.printStackTrace();
        }
        return R.success(fileName);

    }

    @GetMapping("/download")
    public void download(String name,HttpServletResponse response){
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(basePath + name));

            ServletOutputStream outputStream = response.getOutputStream();
            response.setContentType("image/jpeg");

            int len = 0;
            byte[] bytes = new byte[1024];
           while ((len = fileInputStream.read(bytes))!= -1){
               outputStream.write(bytes, 0 ,len);
               outputStream.flush();
           }

           outputStream.close();
           fileInputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }




}
