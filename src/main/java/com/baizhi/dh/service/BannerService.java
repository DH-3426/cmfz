package com.baizhi.dh.service;

import com.baizhi.dh.entity.Banner;

import java.util.HashMap;


public interface BannerService {
    //查所有
    public HashMap<String,Object> queryAll(Integer page, Integer row);
    //删除
    public void delete(Banner banner);
    //修改
    public HashMap<String,Object> update(Banner banner);
    //添加
    public HashMap<String,Object> insert(Banner banner);
    //修改状态
    public void changeStruts(Banner banner);
}
