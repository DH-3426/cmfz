package com.baizhi.dh.service;

import com.baizhi.dh.dao.AdminDao;
import com.baizhi.dh.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminDao adminDao;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Admin selectAdmin(String username) {
        Admin admin = adminDao.selectOne(new Admin(null, username,null));
        return admin;
    }
}
