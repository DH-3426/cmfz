package com.baizhi.dh.service;

import com.baizhi.dh.entity.Chapter;

import java.util.HashMap;
import java.util.List;

public interface ChapterService {
    public HashMap selectAll(String id,Integer page, Integer rows);
    public HashMap edit(Chapter chapter, String edit);
    public List<Chapter> selectOne(Chapter chapter);
}
