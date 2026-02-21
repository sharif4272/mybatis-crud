package com.revo.mybatis.mapper;

import com.revo.mybatis.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    // Create
    void insertUser(User user);

    // Read -
    List<User> findAllUsers();

    // Read -
    User findUserById(@Param("id") Long id);

    // Update
    void updateUser(User user);

    // Delete
    void deleteUser(@Param("id") Long id);

    // Check if email exists (unique validation এর জন্য)
    int countByEmail(@Param("email") String email);
}
