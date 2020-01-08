package com.baizhi.dh.service;

import com.baizhi.dh.entity.Album;

import java.util.HashMap;

public interface AlbumService {
    public HashMap selectAll(Integer page, Integer rows);
    public HashMap edit(Album album,String edit);
    public Album selectOne(String id);
}
