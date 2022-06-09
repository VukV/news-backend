package com.vvukovic9420rn_projekat.repositories.user;

import com.vvukovic9420rn_projekat.entities.User;
import com.vvukovic9420rn_projekat.responses.ArticleCreatorResponse;

import java.util.List;

public interface UserRepository {

    List<User> getAllUsers(Integer page);
    User getUserByEmail(String email);
    void addUser(User user);
    void updateUser(User user);
    void changeStatusById(Integer id, Boolean status);

    ArticleCreatorResponse getArticleCreator(Integer id);
}
