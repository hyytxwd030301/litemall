package com.hyy.service.impl;

import com.hyy.entity.User;
import com.hyy.mapper.UserMapper;
import com.hyy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public Boolean login(String userName, String password) {
        User user=new User();
        user.setUserName(userName);
        user.setPassword(password);
        User user1 = userMapper.findByNameAndPassword(user);
        if (user1!=null){
            return false;
        }else {
            return true;
        }
    }
}
