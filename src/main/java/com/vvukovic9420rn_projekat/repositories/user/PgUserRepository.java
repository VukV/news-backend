package com.vvukovic9420rn_projekat.repositories.user;

import com.vvukovic9420rn_projekat.entities.User;
import com.vvukovic9420rn_projekat.repositories.Postgres;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PgUserRepository extends Postgres implements UserRepository {

    @Override
    public List<User> getAllUsers(Integer page) {
        List<User> users = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();

            int offset = (page - 1) * 10;

            preparedStatement = connection.prepareStatement("SELECT * FROM users OFFSET ? ROWS LIMIT 10");
            preparedStatement.setInt(1, offset);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                users.add(new User(
                        resultSet.getInt("id"), resultSet.getString("password"),
                        resultSet.getString("email"), resultSet.getString("name"),
                        resultSet.getString("surname"), resultSet.getString("type"),
                        resultSet.getBoolean("status"))
                );
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return users;
    }

    @Override
    public User getUserByEmail(String emailQuery) {
        User userToReturn = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE email = ?");
            preparedStatement.setString(1, emailQuery);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                int id = resultSet.getInt("id");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String type = resultSet.getString("type");
                boolean status = resultSet.getBoolean("status");

                userToReturn = new User(id, password, email, name, surname, type, status);
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return userToReturn;
    }

    @Override
    public void addUser(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement("INSERT INTO users (email, name, surname, type, status, password) VALUES(?, ?, ?, ?, ?, ?)", generatedColumns);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getSurname());
            preparedStatement.setString(4, user.getType());
            preparedStatement.setBoolean(5, true);
            preparedStatement.setString(6, user.getPassword());

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
    public void updateUser(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("UPDATE users SET email=?, name=?, surname=?, type=? WHERE id = ?");
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getSurname());
            preparedStatement.setString(4, user.getType());
            preparedStatement.setInt(5, user.getId());

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
    public void changeStatusById(Integer id, Boolean status) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("UPDATE users SET status=? WHERE id = ? AND type='Creator'");
            preparedStatement.setBoolean(1, status);
            preparedStatement.setInt(2, id);

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
}
