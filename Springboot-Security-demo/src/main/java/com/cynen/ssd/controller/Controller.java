package com.cynen.ssd.controller;


import com.cynen.ssd.pojo.Result;
import com.cynen.ssd.pojo.User;
import com.cynen.ssd.service.UserService;
import com.cynen.ssd.util.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/jwt")
public class Controller {


    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping("/login")
    public Result login(String login,String password){
        // 登录用户.
        // User user = userService.findByLoginAndPassword(loginMap.get("phone"),loginMap.get("password"));
        User user = userService.findByMobileAndPassword(login,password);
        if (user != null){
            // 登录成功,签发Token.
            // 查询用户的角色
            List<String> roles = new ArrayList<>();
            roles.add("user");
            String token = jwtUtils.creatJWT(user.getId(),user.getMobile(),roles);
            return new Result(true,"登录成功",token);
        }else {
            return new Result(false,"登录失败");
        }
    }



    @RequestMapping("/show")
    public Result test(String id){
        Claims claims = (Claims) request.getAttribute("claims");
        List<String> roles = (List<String>) claims.get("roles");
        if (roles.contains("admin")){
            userService.deleteById(id);
            return new Result(true,"删除成功");
        }else {
            return new Result(false,"权限不够");
        }
    }
}
