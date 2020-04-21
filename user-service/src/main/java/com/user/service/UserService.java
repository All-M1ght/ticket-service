package com.user.service;

import com.user.model.User;

public interface UserService {
    User findUserById(User user);
    User addNewUser(User user);
    void deleteUser(User user);

}
