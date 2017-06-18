package com.Mapper;

import com.entity.User;

/**
 * Created by donnie on 17-6-18.
 */
public interface UserMapper {
    void insert(User user);
    void delete(long id);
    void update(User user);
    User findById(User user);
}
