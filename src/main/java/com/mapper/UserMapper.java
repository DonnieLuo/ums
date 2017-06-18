package com.mapper;

import com.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by donnie on 17-6-18.
 */
@Mapper
public interface UserMapper {
    void insert(User user);
    void delete(long id);
    void update(User user);
    User findById(long id);
    List<User> list();
}
