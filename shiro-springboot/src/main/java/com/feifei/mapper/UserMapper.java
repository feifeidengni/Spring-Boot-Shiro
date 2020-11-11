package com.feifei.mapper;

import com.feifei.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {
    //查询用户信息
    public User findByUserCode(String userCode);
}
