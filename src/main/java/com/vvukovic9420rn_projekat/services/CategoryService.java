package com.vvukovic9420rn_projekat.services;

import com.vvukovic9420rn_projekat.entities.Category;
import com.vvukovic9420rn_projekat.repositories.article.ArticleRepository;
import com.vvukovic9420rn_projekat.repositories.category.CategoryRepository;

import javax.inject.Inject;
import java.util.List;

public class CategoryService {

    @Inject
    private CategoryRepository categoryRepository;
    @Inject
    private ArticleRepository articleRepository;

    public List<Category> getAllCategories(int page){
        return categoryRepository.getAllCategories(page);
    }

    public List<Category> getAllCategories(){
        return categoryRepository.getAllCategories();
    }

    public Category getCategoryFromArticle(Integer articleId){
        return categoryRepository.getCategoryFromArticle(articleId);
    }

    public void addCategory(Category category){
        categoryRepository.addCategory(category);
    }

    public void updateCategory(Category category){
        categoryRepository.updateCategory(category);
    }

    public boolean deleteCategory(Integer categoryId){
        return categoryRepository.deleteCategoryById(categoryId);
    }
}
