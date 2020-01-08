package com.baizhi.dh.service;

import com.baizhi.dh.entity.Counter;

import java.util.HashMap;


public interface CounterService {
    //查所有
    public HashMap queryAll();
    //删除
    public HashMap delete(Counter counter);
    //修改
    public HashMap update(Counter counter);
    //添加
    public HashMap insert(Counter counter);

}
