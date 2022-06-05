package com.vvukovic9420rn_projekat.services;

import com.vvukovic9420rn_projekat.entities.Article;
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

    public void addCategory(Category category){
        categoryRepository.addCategory(category);
    }

    public void updateCategory(Category category){
        categoryRepository.updateCategory(category);
    }

    public int deleteCategory(Integer categoryId){
        List<Article> articles = articleRepository.getAllArticlesByCategorySortedByDate(categoryId, 1);

        if (!articles.isEmpty()){
            return 403;
        }
        else {
            categoryRepository.deleteCategoryById(categoryId);
            return 200;
        }
    }
}
