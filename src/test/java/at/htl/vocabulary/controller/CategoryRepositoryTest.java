package at.htl.vocabulary.controller;

import at.htl.vocabulary.database.SqlRunner;
import at.htl.vocabulary.model.Category;
import org.assertj.db.api.Assertions;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.sql.DataSource;

import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;
import static org.junit.jupiter.api.Assertions.fail;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
class CategoryRepositoryTest {

    DataSource dataSource = Database.getDataSource();
    static CategoryRepository repository;

    @BeforeAll
    static void beforeAll() {
        SqlRunner.runScript();
        repository = new CategoryRepository();
    }


    @Test
    void t0010_save() {
        Category category = new Category("School");
        repository.save(category);

        Table table = new Table(dataSource, "CATEGORY");
        Assertions.assertThat(table).row(table.getRowsList().size() - 1)
                .value("CAT_NAME").isEqualTo("School");

    }

    @Test
    void t0020_delete() {


        String categoryName = "School";

        Table table = new Table(dataSource, "CATEGORY");
        output(table).toConsole();
        int expectedRows = table.getRowsList().size() - 1;
        repository.deleteByName(categoryName);

        table = new Table(dataSource, "CATEGORY");
        output(table).toConsole();
        assertThat(table).hasNumberOfRows(expectedRows);

        if (repository.findByName(categoryName) != null) {
            fail("CATEGORY " + categoryName + " not deleted from db");
        }

    }

    @Test
    void t0030_findAllTest() {
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
    void t0040_findById() {

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