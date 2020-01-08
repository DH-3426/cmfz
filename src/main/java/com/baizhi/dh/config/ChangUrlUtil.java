package com.baizhi.dh.config;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;

public class ChangUrlUtil {
    public static String changUrl(MultipartFile url,HttpServletRequest request,String path){
        String realPath = request.getSession().getServletContext().getRealPath(path);
        //获取文件夹
        File file = new File(realPath);
        //判断文件夹是否存在
        if(!file.exists()){
            file.mkdirs(); //创建文件夹
        }
        //获取文件名
        String filename = url.getOriginalFilename();
        //给文件加一个时间戳
        String name=new Date().getTime()+"-"+filename;
        //文件上传
        try {
            url.transferTo(new File(realPath,name));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return name;
    }
}
