package com.baizhi.dh;

import com.baizhi.dh.dao.AdminDao;
import com.baizhi.dh.dao.AlbumDao;
import com.baizhi.dh.dao.BannerDao;
import com.baizhi.dh.dao.UserDao;
import com.baizhi.dh.entity.Admin;
import com.baizhi.dh.entity.Banner;
import org.apache.ibatis.session.RowBounds;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@SpringBootTest
@RunWith(SpringRunner.class)
public class CmfzApplicationTests {
    @Autowired
    BannerDao bannerDao;
    @Autowired
    AdminDao adminDao;
    @Autowired
    AlbumDao dao;
    @Autowired
    UserDao dao2;
    @Test
    public void contextLoads() {
       // List<Admin> admins = adminDao.selectAll();
        Admin admin = new Admin(null, "admin", "admin");
        List<Admin> select = adminDao.select(admin);
        System.out.println(select);
    }
    @Test
    public void testBanner(){
        //List<Banner> banners = bannerDao.selectAll();
        //参数2：每页展示多少条
        List<Banner> banners = bannerDao.selectByRowBounds(null, new RowBounds(0, 1));
        System.out.println(banners);
    }
    @Test
    public void testAlbum(){
        Integer integer = dao2.queryAllBySexAndTime("1", 7);
        System.out.println(integer);
    }

}
