package com.vvukovic9420rn_projekat.repositories.category;

import com.vvukovic9420rn_projekat.entities.Category;

import java.util.List;

public interface CategoryRepository {

    List<Category> getAllCategories(Integer page);
    void addCategory(Category category);
    void updateCategory(Category category);
    boolean deleteCategoryById(Integer id);
}
