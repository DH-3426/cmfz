package com.baizhi.dh.controller;

import com.baizhi.dh.entity.Counter;
import com.baizhi.dh.service.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("counter")
public class CounterController {
    @Autowired
    CounterService service;
    //查所有的计数器
    @RequestMapping("queryAll")
    public HashMap queryAll(String uid,String id){
        HashMap hashMap = service.queryAll();
        return hashMap;
    }
    //添加计数器
    @RequestMapping("insert")
    public HashMap insert(String uid,String title){
        Counter counter = new Counter();
        counter.setUserId(uid);
        counter.setTitle(title);
        HashMap hashMap = service.insert(counter);
        return hashMap;
    }
    //修改计数器
    @RequestMapping("update")
    public HashMap update(String uid,String id,long count){
        Counter counter = new Counter();
        counter.setUserId(uid);
        counter.setId(id);
        counter.setCount(count);
        HashMap update = service.update(counter);
        return update;
    }
    //删除计数器
    @RequestMapping("delete")
    public HashMap delete(String uid,String id){
        Counter counter = new Counter();
        counter.setUserId(uid);
        counter.setId(id);
        HashMap delete = service.delete(counter);
        return delete;
    }
}
