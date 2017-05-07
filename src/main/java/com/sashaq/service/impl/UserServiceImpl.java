package com.sashaq.service.impl;

import com.sashaq.dao.UserDao;
import com.sashaq.entity.User;
import com.sashaq.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public User create(User user) {
        int userId = userDao.save(user);

        user.setId(userId);
        user.setPassword(null);

        return  user;
    }

    @Override
    @Transactional(readOnly = true)
    public User getById(Integer userId) {
        return userDao.getById(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public User getByToken(String userToken) {
        return userDao.getByToken(userToken);
    }


    @Override
    @Transactional
    public boolean deleteById(Long userId) {
        //todo delete tokens before delete user
        return 0 != userDao.deleteById(userId);
    }

    @Override
    public List<User> getUninvolvedUsers() {
        return userDao.getUninvolvedUsers();
    }

}
