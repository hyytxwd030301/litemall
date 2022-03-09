package com.hyy.mapper;

import com.hyy.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User findByNameAndPassword(User user);
}
