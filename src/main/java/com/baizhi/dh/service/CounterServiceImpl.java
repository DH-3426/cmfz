package com.baizhi.dh.service;

import com.baizhi.dh.dao.CounterDao;
import com.baizhi.dh.entity.Counter;
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
public class CounterServiceImpl implements CounterService {
    @Autowired
    CounterDao dao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public HashMap queryAll() {
        List<Counter> select = dao.selectAll();
        HashMap hashMap = new HashMap();
        hashMap.put("status",200);
        hashMap.put("counter",select);
        return hashMap;
    }

    @Override
    public HashMap delete(Counter counter) {
        dao.delete(counter);
        List<Counter> select = dao.selectAll();
        HashMap hashMap = new HashMap();
        hashMap.put("status",200);
        hashMap.put("counter",select);
        return hashMap;
    }

    @Override
    public HashMap update(Counter counter) {
        dao.updateByPrimaryKeySelective(counter);
        List<Counter> counters = dao.selectAll();
        HashMap hashMap = new HashMap();
        hashMap.put("status",200);
        hashMap.put("counter",counters);
        return hashMap;
    }

    @Override
    public HashMap insert(Counter counter) {
        String s = UUID.randomUUID().toString();
        counter.setId(s);
        counter.setCreateDate(new Date());
        dao.insert(counter);
        List<Counter> counters = dao.selectAll();
        HashMap hashMap = new HashMap();
        hashMap.put("status",200);
        hashMap.put("counter",counters);
        return hashMap;
    }
}
