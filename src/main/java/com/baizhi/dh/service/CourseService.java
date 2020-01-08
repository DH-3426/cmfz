package com.baizhi.dh.service;

import com.baizhi.dh.entity.Course;

import java.util.HashMap;

public interface CourseService {
    //展示一名用户名下的功课
    public HashMap selectByUid();
    //添加功课
    public HashMap insert(Course course);
    //删除功课
    public HashMap delete(String id);
}
