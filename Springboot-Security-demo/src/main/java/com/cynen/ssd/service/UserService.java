package com.cynen.ssd.service;

import com.cynen.ssd.pojo.User;

public interface UserService {
    User findByLoginAndPassword(String pbone,String password);

    User findByMobileAndPassword(String phone, String password);

    void deleteById(String id);
}
