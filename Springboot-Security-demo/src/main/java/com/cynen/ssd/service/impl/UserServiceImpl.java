package com.cynen.ssd.service.impl;

import com.cynen.ssd.dao.UserDao;
import com.cynen.ssd.pojo.User;
import com.cynen.ssd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private UserDao userDao;

    @Override
    public User findByLoginAndPassword(String phone,String password) {
        return null;
    }

    @Override
    public User findByMobileAndPassword(String phone, String password) {
        // 登录.
        User user = userDao.findByPhoneAndPassword(phone);
        if (user != null && encoder.matches(password,user.getPassword())){
            return user;
        }else {
            return  null;
        }
    }

    @Override
    public void deleteById(String id) {
        System.out.println("======================");
    }


}
