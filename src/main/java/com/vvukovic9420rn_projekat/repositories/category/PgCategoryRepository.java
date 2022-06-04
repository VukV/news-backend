package com.vvukovic9420rn_projekat.repositories.category;

import com.vvukovic9420rn_projekat.entities.Category;
import com.vvukovic9420rn_projekat.repositories.Postgres;

import java.util.List;

public class PgCategoryRepository extends Postgres implements CategoryRepository {

    @Override
    public List<Category> getAllCategories(Integer page) {
        return null;
    }

    @Override
    public void addCategory(Category category) {

    }

    @Override
    public void updateCategory(Category category) {

    }

    @Override
    public void deleteCategoryById(Integer id) {

    }
}
