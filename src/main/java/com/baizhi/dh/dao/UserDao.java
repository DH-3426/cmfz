package com.baizhi.dh.dao;

import com.baizhi.dh.entity.MapDto;
import com.baizhi.dh.entity.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserDao extends Mapper<User> {
    public Integer queryAllBySexAndTime(@Param("sex") String sex,@Param("day") Integer day);
    public List<MapDto> queryAllBySexAndLocation(@Param("sex") String sex);
    public List<User> queryAllByTime();
}
