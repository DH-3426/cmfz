package com.baizhi.dh.service;

import com.baizhi.dh.dao.CourseDao;
import com.baizhi.dh.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseDao dao;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public HashMap selectByUid() {
        List select = dao.selectAll();
        HashMap hashMap = new HashMap();
        hashMap.put("status",200);
        hashMap.put("option",select);
        return hashMap;
    }

    @Override
    public HashMap insert(Course course) {
        String s = UUID.randomUUID().toString();
        course.setId(s);
        course.setCreateDate(new Date());
        dao.insert(course);
        List list = dao.selectAll();
        HashMap hashMap = new HashMap();
        hashMap.put("status",200);
        hashMap.put("option",list);
        return hashMap;
    }

    @Override
    public HashMap delete(String id) {
        dao.deleteByPrimaryKey(id);
        List list = dao.selectAll();
        HashMap hashMap = new HashMap();
        hashMap.put("status",200);
        hashMap.put("option",list);
        return hashMap;
    }
}
