package at.htl.project.controller;

import at.htl.project.model.Category;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Period;
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

        try (Connection connection = dataSource.getConnection()) {
            String sql = "DELETE FROM CATEGORY WHERE CAT_ID=" + id;
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);

            if (statement.executeUpdate() == 0) {
                throw new SQLException("Delete from CATEGORY failed, no rows affected");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Category> findAll() {

        List<Category> categories = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT CAT_ID, CAT_NAME FROM CATEGORY";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Long id = result.getLong("ID");
                String name = result.getString("NAME");
                categories.add(new Category(name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }

    @Override
    public Category findById(long id) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM CATEGORY WHERE ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                Category selectedCategory = new Category();
                selectedCategory.setId(id);
                resultSet.next();
                selectedCategory.setName("NAME");
                return selectedCategory;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Category> findByName(String name) {
        List<Category> categories = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT ID FROM CATEGORY WHERE NAME = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next() == true) {
                    Category selectedCategory = new Category();
                    selectedCategory.setId(resultSet.getLong("id"));
                    categories.add(selectedCategory);
                }
                return categories;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
