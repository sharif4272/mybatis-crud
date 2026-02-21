package com.revo.mybatis.service;

import com.revo.mybatis.mapper.UserMapper;
import com.revo.mybatis.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getAllUsers() {
        return userMapper.findAllUsers();
    }

    @Override
    public User getUserById(Long id) {
        return userMapper.findUserById(id);
    }

    @Override
    public void saveUser(User user) {
        userMapper.insertUser(user);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public void deleteUser(Long id) {
        userMapper.deleteUser(id);
    }

    @Override
    public boolean isEmailExists(String email) {
        return userMapper.countByEmail(email) > 0;
    }
}
