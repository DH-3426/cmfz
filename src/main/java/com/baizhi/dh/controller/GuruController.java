package com.baizhi.dh.controller;

import com.baizhi.dh.entity.Guru;
import com.baizhi.dh.service.GuruService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("guru")
@Transactional
public class GuruController {
    @Autowired
    GuruService service;
    @RequestMapping("queryAll")
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<Guru> queryAll(){
        List<Guru> hashMap = service.showAll();
        return hashMap;
    }
    //展示上师
    @RequestMapping("queryAllGuru")
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public HashMap queryAllGuru(){
        List<Guru> list = service.showAll();
        HashMap hashMap = new HashMap();
        hashMap.put("status",200);
        hashMap.put("message","");
        hashMap.put("list",list);
        return hashMap;
    }
}
