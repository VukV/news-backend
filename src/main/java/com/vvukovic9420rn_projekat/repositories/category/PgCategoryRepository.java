package com.vvukovic9420rn_projekat.repositories.category;

import com.vvukovic9420rn_projekat.entities.Category;
import com.vvukovic9420rn_projekat.repositories.Postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PgCategoryRepository extends Postgres implements CategoryRepository {

    @Override
    public List<Category> getAllCategories(Integer page) {
        List<Category> categories = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM categories OFFSET (? - 1) * 10 ROWS LIMIT 10");
            preparedStatement.setInt(1, page);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                categories.add(new Category(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("description"))
                );
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return categories;
    }

    @Override
    public void addCategory(Category category) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement("INSERT INTO categories (name, description) VALUES(?, ?)", generatedColumns);
            preparedStatement.setString(1, category.getName());
            preparedStatement.setString(2, category.getDescription());

            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
    }

    @Override
    public void updateCategory(Category category) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("UPDATE categories SET name=?, description=? WHERE id = ?");
            preparedStatement.setString(1, category.getName());
            preparedStatement.setString(2, category.getDescription());
            preparedStatement.setInt(3, category.getId());

            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
    }

    @Override
    public boolean deleteCategoryById(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int count = 0;

        try {
            connection = this.newConnection();

            //DELETE FROM categories WHERE id = ? AND WHERE id NOT IN (SELECT category_id FROM posts WHERE category_id = ?)
            preparedStatement = connection.prepareStatement("DELETE FROM categories WHERE id = ? AND id NOT IN (SELECT category_id FROM articles WHERE category_id = ?)");
            preparedStatement.setInt(1, id);
            count = preparedStatement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }

        if(count == 0){
            return false;
        }
        return true;
    }
}
