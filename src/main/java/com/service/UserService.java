package com.service;

import com.entity.User;
import com.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by donnie on 17/6/18.
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userDao;

    public void delete(long id) {
        userDao.delete(id);
    }
    public User findById(long id){
        return userDao.findById(id);
//        return new User();
    }
    public void update(User user){
        userDao.update(user);
    }
    public void insert(User user) {
        userDao.insert(user);
    }
    public List<User> list() {
        return userDao.list();
    }

}
