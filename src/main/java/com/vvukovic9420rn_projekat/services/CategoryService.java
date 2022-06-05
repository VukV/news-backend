package com.vvukovic9420rn_projekat.services;

import com.vvukovic9420rn_projekat.entities.Category;
import com.vvukovic9420rn_projekat.repositories.category.CategoryRepository;

import javax.inject.Inject;
import java.util.List;

public class CategoryService {

    @Inject
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories(int page){
        return categoryRepository.getAllCategories(page);
    }

    public void addCategory(Category category){
        categoryRepository.addCategory(category);
    }

    public void updateCategory(Category category){
        categoryRepository.updateCategory(category);
    }

    public void deleteCategory(Integer categoryId){
        //TODO
    }
}
