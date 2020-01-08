package com.baizhi.dh.controller;

import com.baizhi.dh.entity.Course;
import com.baizhi.dh.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("course")
public class CourseController {
    @Autowired
    CourseService service;
    @RequestMapping("queryAll")
    public HashMap queryAll(String uid){
        Course course = new Course();
        course.setId(uid);
        HashMap hashMap = service.selectByUid();
        return hashMap;
    }
    @RequestMapping("insert")
    public HashMap insert(String uid,String title){
        Course course = new Course();
        course.setUserId(uid);
        course.setTitle(title);
        HashMap insert = service.insert(course);
        return insert;
    }
    @RequestMapping("delete")
    public HashMap delete(String id,String uid){
        HashMap delete = service.delete(id);
        return delete;
    }
}
