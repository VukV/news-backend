package com.vvukovic9420rn_projekat.repositories.user;

import com.vvukovic9420rn_projekat.entities.User;

import java.util.List;

public interface UserRepository {

    List<User> getAllUsers(Integer page);
    User getUserByEmail(String email);
    void addUser(User user);
    void updateUser(User user);
    void changeStatusById(Integer id, Boolean status);

}
