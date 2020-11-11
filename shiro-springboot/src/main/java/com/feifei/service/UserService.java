package com.feifei.service;

import com.feifei.pojo.User;

public interface UserService {
    public User findByUserCode(String userCode);
}
