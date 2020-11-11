package com.feifei.service;

import com.feifei.mapper.UserMapper;
import com.feifei.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements  UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User findByUserCode(String userCode) {
        return this.userMapper.findByUserCode(userCode);
    }
}
