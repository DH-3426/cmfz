package com.baizhi.dh.service;

import com.baizhi.dh.entity.Guru;

import java.util.List;

public interface GuruService {
    public List<Guru> showAll();

    /**
     * 18. 添加关注上师
     * @param uid       用户id
     * @param id        上师id
     * @return          ["status":"200","message":"","list":{"id":"","name":"","photo":"","nick_name":""},{},{},{}]
     */
    public List<Guru> installGuruByUser(String uid,String id);
}
