package com.baizhi.dh.service;

import com.baizhi.dh.dao.AlbumDao;
import com.baizhi.dh.entity.Album;
import org.apache.ibatis.session.RowBounds;
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
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    AlbumDao dao;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public HashMap selectAll(Integer page, Integer rows) {
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
        List<Album> albums = dao.selectByRowBounds(null, new RowBounds((page - 1)*rows, rows));
        map.put("rows",albums);
        return map;
    }

    @Override
    public HashMap edit(Album album,String edit) {
        HashMap hashMap = new HashMap();
        if(edit.equals("add")){
            String s = UUID.randomUUID().toString();
            album.setId(s);
            album.setCreateDate(new Date());
            dao.insert(album);
            hashMap.put("albumId",album.getId());
        System.out.println(hashMap+"*/*/*/*/*/*/*/*/*/*/");
        }else if(edit.equals("del")){
            dao.delete(album);
        }else{
            System.out.println(edit+album);
            dao.updateByPrimaryKeySelective(album);
            hashMap.put("albumId",album.getId());
        }
        return hashMap;
    }

    @Override
    public Album selectOne(String id) {
        Album album = new Album();
        album.setId(id);
        Album album1 = dao.selectOne(album);
        return album1;
    }
}
