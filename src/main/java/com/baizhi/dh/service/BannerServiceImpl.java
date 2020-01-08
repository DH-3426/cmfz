package com.baizhi.dh.service;

import com.baizhi.dh.dao.BannerDao;
import com.baizhi.dh.entity.Banner;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class BannerServiceImpl implements BannerService {
    @Autowired
    BannerDao bannerDao;



    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public HashMap<String, Object> queryAll(Integer page, Integer row) {
        int size = bannerDao.selectCount(null);
        HashMap<String, Object> map = new HashMap<>();
        //总条数
        map.put("records",size);
        Integer total=0;
        if(size%row==0){
            total=size/row;
        }else{
            total=size/row+1;
        }
        //总页数
        map.put("total",total);
        //当前页
        map.put("page",page);
        //要展示的数据
        List<Banner> banners = bannerDao.selectByRowBounds(null, new RowBounds((page - 1)*row, row));
        map.put("rows",banners);
        return map;
    }

    @Override
    public void delete(Banner banner) {
        bannerDao.delete(banner);
    }

    @Override
    public HashMap<String,Object> update(Banner banner) {
        HashMap<String, Object> map = new HashMap<>();
        bannerDao.updateByPrimaryKeySelective(banner);
        map.put("bannerId",banner.getId());
        return  map;
    }

    @Override
    public HashMap<String,Object> insert(Banner banner) {
        HashMap<String, Object> map = new HashMap<>();
        String s = UUID.randomUUID().toString();
        banner.setId(s);
        banner.setStatus("2");
        banner.setCreateDate(new Date());
        bannerDao.insert(banner);
        map.put("bannerId",s);
        return map;
    }

    @Override
    public void changeStruts(Banner banner) {
        String status = banner.getStatus();
        if (status.equals("1")){
            banner.setStatus("2");
        }else{
            banner.setStatus("1");
        }
        bannerDao.updateByPrimaryKeySelective(banner);
    }
}
