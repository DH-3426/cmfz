package com.baizhi.dh.service;

import com.baizhi.dh.dao.GuruDao;
import com.baizhi.dh.entity.Guru;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class GuruServiceImpl implements GuruService {
    @Autowired
    GuruDao guruDao;

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Guru> showAll() {
        List<Guru> articles = guruDao.selectAll();
        return articles;
    }

    /**
     * 18. 添加关注上师
     *
     * @param uid 用户id
     * @param id  上师id
     * @return ["status":"200","message":"","list":{"id":"","name":"","photo":"","nick_name":""},{},{},{}]
     * 注释：redis存储当前用户关注的上师的信息---用户ID,上师id【key,value】
     */
    @Override
    public List<Guru> installGuruByUser(String uid, String id) {
        //添加上师id到redis
        redisTemplate.opsForList().leftPush(uid,id);
        //获得redis中所有上师id
        List range = redisTemplate.opsForList().range(id, 0, -1);
        //根据上师id查询上师信息集合
        Example example = new Example(Guru.class);
        example.and().andIn(null, Arrays.asList(range));
        return guruDao.selectByExample(example);
    }
}
