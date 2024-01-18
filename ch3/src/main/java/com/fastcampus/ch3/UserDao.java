package com.fastcampus.ch3;

@SuppressWarnings("unused")
public interface UserDao {
    int deleteUser(String id);

    User selectUser(String id);

    int insertUser(User user);

    int updateUser(User user);
}