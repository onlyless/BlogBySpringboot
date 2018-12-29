package com.djangoz.blog.service;

import com.djangoz.blog.DAO.UserDao;
import com.djangoz.blog.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public boolean login(String username,String password){
        User user = userDao.findByUsernameAndPassword(username,password);
        return !(user==null);
    }

}
