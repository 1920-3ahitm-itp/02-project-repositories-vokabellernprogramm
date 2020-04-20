package at.htl.project.controller;

import at.htl.project.model.Category;
import at.htl.project.model.Word;
import org.assertj.db.api.Assertions;
import org.assertj.db.type.Row;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.Test;
import javax.sql.DataSource;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CategoryRepositoryTest {

    DataSource dataSource = Database.getDataSource();
    CategoryRepository repository = new CategoryRepository();

    @Test
    void save() {
        Category category = new Category("Hi");
        repository.save(category);

        Table table = new Table(dataSource, "CATEGORY");
        Assertions.assertThat(table).row(table.getRowsList().size() - 1)
                .value("CAT_NAME").isEqualTo("Hi");

    }

    @Test
    void delete() {

    }

    @Test
    void findAll() {

    }

    @Test
    void findById() {

    }

    @Test
    void findByName() {
    }
}