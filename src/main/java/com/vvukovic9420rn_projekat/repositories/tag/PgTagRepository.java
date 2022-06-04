package com.vvukovic9420rn_projekat.repositories.tag;

import com.vvukovic9420rn_projekat.entities.Tag;
import com.vvukovic9420rn_projekat.repositories.Postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PgTagRepository extends Postgres implements TagRepository {

    @Override
    public void addTag(Tag tag) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement("INSERT INTO tags (name) VALUES(?)", generatedColumns);
            preparedStatement.setString(1, tag.getName());

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
