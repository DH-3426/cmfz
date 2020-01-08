package com.baizhi.dh.service;

import com.baizhi.dh.dao.ChapterDao;
import com.baizhi.dh.entity.Chapter;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    ChapterDao dao;
    @Override
    public HashMap selectAll(String id, Integer page, Integer rows) {
        Chapter chapter = new Chapter();
        chapter.setAlbumId(id);
        int i = dao.selectCount(null);
        HashMap<String, Object> map = new HashMap<>();
        //总条数
        map.put("records",i);
        Integer total=0;
        if(i%rows==0){
            total=i/rows;
        }else{
            total=i/rows+1;
        }
        //总页数
        map.put("total",total);
        //当前页
        map.put("page",page);
        //要展示的数据
        List<Chapter> chapters = dao.selectByRowBounds(chapter, new RowBounds((page - 1)*rows, rows));
        map.put("rows",chapters);
        return map;
    }

    @Override
    public HashMap edit(Chapter chapter, String edit) {
        HashMap hashMap = new HashMap();
        if(edit.equals("add")){
            String s = UUID.randomUUID().toString();
            chapter.setId(s);
            chapter.setCreateTime(new Date());
            dao.insert(chapter);
        }else if(edit.equals("del")){
            dao.delete(chapter);
        }else{
            dao.updateByPrimaryKeySelective(chapter);
        }
        hashMap.put("albumId",chapter.getId());
        return hashMap;
    }

    @Override
    public List<Chapter> selectOne(Chapter chapter) {
        List<Chapter> chapters = dao.selectByExample(chapter);
        return chapters;
    }
}
