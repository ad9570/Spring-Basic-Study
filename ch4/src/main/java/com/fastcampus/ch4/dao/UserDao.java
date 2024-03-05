package com.fastcampus.ch4.dao;

import com.fastcampus.ch4.domain.*;

public interface UserDao {
    User selectUser(String id) throws Exception;
}