package at.htl.vocabulary.controller;

import at.htl.vocabulary.model.Category;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepository implements Repository<Category> {

    private final DataSource dataSource = Database.getDataSource();

    @Override
    public void save(Category category) {

        // 1. Überprüfe, ob das Category-Objekt eine ID hat.
        if (category.getId() == null) {
            // 2. Wenn nein -> Neu anlegen mit INSERT INTO …
            create(category);
        }  else {
            // 3. Wenn ja -> suche in der Tabelle CATEGORY nach einer Zeile mit deID des Category-Objekts und ändere den CAT_NAME
            update(category);

        }
    }

    private int create(Category category) {
        int generatedKey = 0;
        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO category (cat_name) VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, category.getName());

            if (statement.executeUpdate() == 0) {
                throw new SQLException("Insert of CATEGORY failed, no rows affected");
            }

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                generatedKey = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return generatedKey;
    }

    /**
     * Bei einer gegebenen Id wird der Text der Category auf den neuen Wert geändert
     *
     * @param category
     */
    private void update(Category category) {
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
    public void delete(int id) {

        try (Connection connection = dataSource.getConnection()) {
            String sql = "DELETE FROM category WHERE cat_id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            if (statement.executeUpdate() == 0) {
                throw new SQLException("Delete from CATEGORY failed, no rows affected");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteByName(String categoryName) {
        Category toDelete = findByName(categoryName);
        delete(toDelete.getId());
    }

    @Override
    public List<Category> findAll() {

        List<Category> categories = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT cat_id, cat_name FROM category";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Long id = result.getLong("CAT_ID");
                String name = result.getString("CAT_NAME");
                categories.add(new Category(name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public Category findById(int id) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM category WHERE cat_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                Category selectedCategory = new Category();
                selectedCategory.setId(id);
                resultSet.next();
                selectedCategory.setName("CAT_NAME");
                return selectedCategory;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

//    @Override
//    public List<Category> findByName(String name) {
//        List<Category> categories = new ArrayList<>();
//        try (Connection connection = dataSource.getConnection()) {
//            String sql = "SELECT CAT_ID FROM CATEGORY WHERE CAT_NAME = ?";
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setString(1, name);
//            try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                while (resultSet.next()) {
//                    Category selectedCategory = new Category();
//                    selectedCategory.setId(resultSet.getLong("id"));
//                    categories.add(selectedCategory);
//                }
//                return categories;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public Category findByName(String name) {
        List<Category> categories = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT cat_id, cat_name FROM category WHERE cat_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                while (resultSet.next()) {
//                    Category selectedCategory = new Category();
//                    selectedCategory.setId(resultSet.getLong("id"));
//                    categories.add(selectedCategory);
//                }
//                return categories;
                /**
                 * Wir nehmen an, dass es jede CATEGORY nur einmal gibt
                 * (vgl UNIQUE Constraint in Tabelle CATEGORY)
                 */
                resultSet.next();
                int id = resultSet.getInt(1);
                String catName = resultSet.getString(2);
                return new Category(id, catName);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

}
