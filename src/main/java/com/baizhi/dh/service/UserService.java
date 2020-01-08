package com.baizhi.dh.service;

import com.baizhi.dh.entity.User;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

public interface UserService {
    public HashMap queryAllByTime();
    public HashMap<String,Object> queryAll(Integer page, Integer row);
    public HashMap queryAllByLocation();
    public HashMap login(String photo,String password);
    public HashMap updateOne(User user, HttpSession session);
    public HashMap updateOneById(User user);
    public List<User> queryFiveByTime();





}
