package com.baizhi.dh.controller;

import com.baizhi.dh.entity.Banner;
import com.baizhi.dh.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

@Controller
@Transactional
public class BannerController {
    @Autowired
    BannerService service;
    @RequestMapping("queryAll")
    @ResponseBody
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public HashMap<String, Object> queryAll(Integer page, Integer rows){
        HashMap<String, Object> map = service.queryAll(page, rows);
        return map;
    }
    //增删改
    @RequestMapping("save")
    @ResponseBody
    public HashMap<String,Object> save(Banner banner, String oper){
        HashMap hashMap = new HashMap();
        if(oper.equals("add")){
            hashMap = service.insert(banner);
        }else if(oper.equals("del")){
           service.delete(banner);
        }else{
            hashMap=service.update(banner);
        }
        return hashMap;
    }
    //修状态
    @RequestMapping("changeStruts")
    public void changeStruts(String id,String status){
        Banner banner = new Banner();
        banner.setId(id);
        banner.setStatus(status);
        service.changeStruts(banner);
    }
    @RequestMapping("updatePath")
    @ResponseBody
    public void updatePth(MultipartFile url, String id, HttpServletRequest request){
        //1.根据相对路径获取绝对路径
        String realPath = request.getSession().getServletContext().getRealPath("/upload/photo");
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
        //执行修改  修改文件的路径
        Banner banner = new Banner();
        banner.setId(id);
        banner.setUrl(name);
        service.update(banner);
    }
}
