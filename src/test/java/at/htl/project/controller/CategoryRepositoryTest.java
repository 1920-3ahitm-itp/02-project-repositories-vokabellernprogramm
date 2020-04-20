package at.htl.project.controller;

import at.htl.project.model.Category;
import at.htl.project.model.Word;
import org.assertj.db.api.Assertions;
import org.assertj.db.type.Row;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import javax.sql.DataSource;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CategoryRepositoryTest {

    DataSource dataSource = Database.getDataSource();
    static CategoryRepository repository;

    @BeforeAll
    static void beforeAll(){
        repository = new CategoryRepository();
    }


    @Test
    void save() {
        Category category = new Category("School");
        repository.save(category);

        Table table = new Table(dataSource, "CATEGORY");
        Assertions.assertThat(table).row(table.getRowsList().size() - 1)
                .value("CAT_NAME").isEqualTo("School");

    }

    @Test
    void delete() {
        Category category = new Category("School");

        Table table = new Table(dataSource, "School");
        int expectedRows = table.getRowsList().size() - 1;
        repository.delete(expectedRows);

        table = new Table(dataSource, "School");

        assertThat(table).hasNumberOfRows(expectedRows);
        assertThat(table).row(expectedRows - 1)
                .value("id").isNotEqualTo(category.getId());

    }

    @Test
    void findAllTest() {
//        List<Category> categories = repository.findAll();
//
//        Table table = new Table(dataSource, "Category");
//        List<Category> expected = new ArrayList<>();
//
//        for(Row row: table.getRowsList()){
//            int id = Integer.parseInt(row.getColumnValue("id").getValue().toString());
//            String name = (String) row.getColumnValue("name").getValue().toString();
//            Category category = new Category(name);
//            expected.add(category);
//        }
//
//       assertThat(categories).containsExactly(expected.toArray(new Category[0]));
    }

    @Test
    void findById() {

//        List<Category> categories = repository.findAll() ;
//        categories.stream().forEach(repository::save);
//
//        Table categoryTable = new Table(dataSource, "Category");
//        org.assertj.db.api.Assertions.assertThat(categoryTable).hasNumberOfRows(3);
//
//        Category expectedCateory = new Category("School");
//        expectedCateory.setId(4L);
//
//
//        Category actualCategory = repository.findById(4);
//
//        assertThat(actualCategory).isEqualTo(expectedCateory);
//        assertThat(actualCategory.getId()).isEqualTo(expectedCateory.getId());

    }
}