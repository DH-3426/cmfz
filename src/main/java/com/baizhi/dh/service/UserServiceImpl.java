package com.baizhi.dh.service;

import com.baizhi.dh.dao.UserDao;
import com.baizhi.dh.entity.MapDto;
import com.baizhi.dh.entity.User;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao dao;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public HashMap queryAllByTime() {
        HashMap hashMap = new HashMap();
        ArrayList arrayList = new ArrayList();
        arrayList.add(dao.queryAllBySexAndTime("0", 1));
        arrayList.add(dao.queryAllBySexAndTime("0", 7));
        arrayList.add(dao.queryAllBySexAndTime("0", 30));
        arrayList.add(dao.queryAllBySexAndTime("0", 365));
        hashMap.put("man",arrayList);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(dao.queryAllBySexAndTime("1", 1));
        arrayList2.add(dao.queryAllBySexAndTime("1", 7));
        arrayList2.add(dao.queryAllBySexAndTime("1", 30));
        arrayList2.add(dao.queryAllBySexAndTime("1", 365));
        hashMap.put("women",arrayList2);
        return hashMap;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public HashMap<String, Object> queryAll(Integer page, Integer row) {
            int size = dao.selectCount(null);
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
            List<User> users = dao.selectByRowBounds(null, new RowBounds((page - 1) * row, row));
            map.put("rows",users);
            return map;

    }

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public HashMap queryAllByLocation() {
        HashMap hashMap = new HashMap();
        List<MapDto> mapDtos = dao.queryAllBySexAndLocation("0");
        List<MapDto> mapDtos2 = dao.queryAllBySexAndLocation("1");
        hashMap.put("man",mapDtos);
        hashMap.put("women",mapDtos2);
        return hashMap;
    }
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    //登录
    public HashMap login(String photo, String password) {
        HashMap hashMap = new HashMap();
        User user = new User();
        user.setPhone(photo);
        user.setPassword(password);
        User user1 = dao.selectOne(user);
        if (user1!=null){
            hashMap.put("status",200);
            hashMap.put("user",user1);
        }else{
            hashMap.put("status",-200);
            hashMap.put("message","账号或密码错误，请重新输入");
        }
        return hashMap;
    }
    //补全信息
    @Override
    public HashMap updateOne(User user, HttpSession session) {
        User user1 = (User) session.getAttribute("user");
        user.setPhone(user1.getPhone());
        HashMap hashMap = new HashMap();
        try{
            dao.updateByPrimaryKeySelective(user);
            User user2 = dao.selectOne(user);
            hashMap.put("status",200);
            hashMap.put("user",user2);
        }catch (Exception e){
            e.printStackTrace();
            hashMap.put("status",-200);
            hashMap.put("message","");
        }
        return hashMap;
    }
    @Override
    public HashMap updateOneById(User user) {
        HashMap hashMap = new HashMap();
        try{
            dao.updateByPrimaryKeySelective(user);
            User user2 = dao.selectOne(user);
            hashMap.put("status",200);
            hashMap.put("user",user2);
        }catch (Exception e){
            e.printStackTrace();
            hashMap.put("status",-200);
            hashMap.put("message","");
        }
        return hashMap;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<User> queryFiveByTime() {
        List<User> users = dao.queryAllByTime();
        return users;
    }


}
