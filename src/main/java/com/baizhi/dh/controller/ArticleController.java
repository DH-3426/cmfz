package com.baizhi.dh.controller;

import com.baizhi.dh.config.HttpUtil;
import com.baizhi.dh.entity.Article;
import com.baizhi.dh.service.ArticleService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping("/article")
@Transactional
public class ArticleController {
    @Autowired
    ArticleService service;
    @RequestMapping("queryAll")
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public HashMap queryAll(Integer page,Integer rows){
        HashMap hashMap = service.selectAll(page, rows);
        return hashMap;
    }
    @RequestMapping("edit")
    public Integer edit(Article article,MultipartFile inputfile,HttpServletRequest request){
        System.out.println(article+"传递参数");
        String edit2=null;
        if(article.getId().equals("")){
            edit2="add";
        }else{
            edit2="edit";
        }
        service.edit(article, edit2,inputfile,request);
        return 1;
    }
    @RequestMapping("delete")
    public Integer delete(String id){
        String edit2="del";
        Article article = new Article();
        article.setId(id);
        service.delete(article);
        return 1;
    }
    @RequestMapping("uploadImg")
    public HashMap uploadImg(MultipartFile imgFile, HttpSession session, HttpServletRequest request){
        HashMap hashMap = new HashMap();
        String realPath = session.getServletContext().getRealPath("/upload/articleImg/");
        File file1 = new File(realPath);
        System.out.println(file1+"真实路径");
        //如果文件夹不存在则创建文件夹
        if(!file1.exists()){
            file1.mkdirs();
        }
        String http = HttpUtil.getHttp(imgFile,request,"/upload/articleImg/");
        try {
            hashMap.put("error",0);
            hashMap.put("url",http);
        }catch (Exception e){
            hashMap.put("error",1);
            e.printStackTrace();
        }
        return hashMap;
    }
    @RequestMapping("showAllImg")
    public HashMap showAllImg(HttpServletRequest request,HttpSession session){
        HashMap hashMap = new HashMap();
        hashMap.put("current_url",request.getContextPath()+"/upload/articleImg/");
        String realPath = session.getServletContext().getRealPath("/upload/articleImg/");
        File file = new File(realPath);
        File[] files = file.listFiles();
        hashMap.put("total_count",files.length);
        ArrayList arrayList = new ArrayList();
        for (File file1 : files) {
            HashMap fileMap = new HashMap();
            fileMap.put("is_dir",false);
            fileMap.put("has_file",false);
            fileMap.put("filesize",file1.length());
            fileMap.put("is_photo",true);
            String name = file1.getName();
            String extension = FilenameUtils.getExtension(name);
            fileMap.put("filetype",extension);
            fileMap.put("filename",name);
            // 通过字符串拆分获取时间戳
            String time = name.split("_")[0];
            // 创建SimpleDateFormat对象 指定yyyy-MM-dd hh:mm:ss 样式
            //  simpleDateFormat.format() 获取指定样式的字符串(yyyy-MM-dd hh:mm:ss)
            // format(参数)  参数:时间类型   new Date(long类型指定时间)long类型  现有数据:字符串类型时间戳
            // 需要将String类型 转换为Long类型 Long.valueOf(str);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String format = simpleDateFormat.format(new Date(Long.valueOf(time)));
            fileMap.put("datetime",format);
            arrayList.add(fileMap);
        }
        hashMap.put("file_list",arrayList);
        return hashMap;
    }
    //文章详情
    @RequestMapping("selectOne")
    public HashMap selectOne(String uid,String id){
        Article article = service.selectOne(id);
        HashMap hashMap = new HashMap();
        hashMap.put("status",200);
        hashMap.put("article",article);
        return  hashMap;
    }




}
