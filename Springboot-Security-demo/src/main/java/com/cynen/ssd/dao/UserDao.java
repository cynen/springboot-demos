package com.cynen.ssd.dao;


import com.cynen.ssd.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

@Mapper
public interface UserDao {

    @Select({
            "SELECT * FROM tb_user where mobile=#{phone}",
    })
    User findByPhoneAndPassword(String phone);

}
