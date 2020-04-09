package at.htl.project.controller;

import at.htl.project.model.Category;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepository implements Repository<Category> {

    private DataSource dataSource = Database.getDataSource();

    @Override
    public void save(Category category) {

        try (Connection connection = dataSource.getConnection()) {
            String sql = "UPDATE CATEGORY SET CAT_NAME=? WHERE CAT_ID=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, category.getId());
            statement.setString(2, category.getName());

            if (statement.executeUpdate() == 0) {
                throw new SQLException("Update of CATEGORY failed, no rows affected");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List<Category> findAll() {
        return null;
    }

    @Override
    public Category findById(long id) {
        return null;
    }
}
