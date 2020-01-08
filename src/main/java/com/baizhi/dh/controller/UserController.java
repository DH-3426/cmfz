package com.baizhi.dh.controller;

import com.baizhi.dh.config.HttpUtil;
import com.baizhi.dh.entity.User;
import com.baizhi.dh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService service;
    @RequestMapping("queryAllByTime")
    public HashMap queryAllByTime(){
        HashMap hashMap = service.queryAllByTime();
        return hashMap;
    }
    @RequestMapping("queryAllByLocation")
    public HashMap queryAllByLocation(){
        HashMap hashMap = service.queryAllByLocation();
        System.out.println(hashMap);
        return hashMap;
    }
    @RequestMapping("queryAll")
    public HashMap<String, Object> queryAll(Integer page, Integer rows){
        HashMap<String, Object> map = service.queryAll(page, rows);
        return map;
    }

    @RequestMapping("login")
    public HashMap login(String phone,String pasword){
        HashMap login = service.login(phone, pasword);
        return login;
    }
    //注册接口  操作Redis
    public HashMap insert(String code){
        HashMap hashMap = new HashMap();
        Jedis jedis = new Jedis("192.168.26.15", 7000);
        String code1 = jedis.get("code");
        if(code==code1){
            hashMap.put("status",200);
            hashMap.put("message","");
        }else{
            hashMap.put("status",-200);
            hashMap.put("message","验证码错误或者已过期，请重新获取");
        }
        return hashMap;
    }
    //补充个人信息
    public HashMap insertAll(User user, MultipartFile file, HttpServletRequest request, HttpSession session){
        String path="/upload/userImg/";
        String http = HttpUtil.getHttp(file, request, path);
        user.setPhoto(http);
        HashMap hashMap = service.updateOne(user, session);
        return hashMap;
    }
    //一级页面展示
    //金刚道友
    @RequestMapping("queryFiveByTime")
    public List<User> queryFiveByTime(){
        List<User> users = service.queryFiveByTime();
        return users;
    }
    //修改个人信息
    @RequestMapping("updateOneUser")
    public HashMap updateOneUser(User user){
        HashMap hashMap = service.updateOneById(user);
        return hashMap;
    }
}
