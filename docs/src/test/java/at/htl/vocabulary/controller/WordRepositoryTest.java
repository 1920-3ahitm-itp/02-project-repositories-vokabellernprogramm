package at.htl.vocabulary.controller;

import at.htl.vocabulary.database.SqlRunner;
import at.htl.vocabulary.model.Category;
import at.htl.vocabulary.model.Word;
import org.assertj.db.api.Assertions;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.*;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;
import static org.junit.jupiter.api.Assertions.fail;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class WordRepositoryTest {

  DataSource dataSource = Database.getDataSource();
  static WordRepository repository;

  @BeforeAll
  static void beforeAll() {
    SqlRunner.runScript();
    repository = new WordRepository();
  }

  @Test
  @Order(1)
  void t0010_save(){

    Word wrd = new Word("Schule","school");
    Table table = new Table(dataSource, "WORD");
    output(table).toConsole();
    repository.save(wrd);
    table = new Table(dataSource, "WORD");
    output(table).toConsole();

    Assertions.assertThat(table).row(table.getRowsList().size() - 1)
            .value("WRD_ENGLISH").isEqualTo("school");
  }

  @Test
  @Order(2)
  void t0020_delete() {
    Word wrd = new Word("Schule","school");

    Table table = new Table(dataSource, "WORD");
    output(table).toConsole();
    int expectedRows = table.getRowsList().size() - 1;
    repository.deleteByEnglishWrd(wrd.getEnglishWord());

    table = new Table(dataSource, "WORD");
    output(table).toConsole();
    Assertions.assertThat(table).hasNumberOfRows(expectedRows);

    if (repository.findByName(wrd.getEnglishWord()) != null) {
      fail("WORD " + wrd.getEnglishWord() + " not deleted from db");
    }
  }


  @Test
  @Order(3)
  void t0030_findAll() {

    int allRows = repository.findAll().size();
    Table table = new Table(dataSource, "WORD");
    int tableRows = table.getRowsList().size();

    org.assertj.core.api.Assertions.assertThat(allRows).isEqualTo(tableRows);
  }

  @Test
  @Order(4)
  void t0040_findByName(){

    Table table = new Table(dataSource, "WORD");

    Word wrd = repository.findByName("Plate");
    output(table).toConsole();

    String [] expected = {String.valueOf(wrd.getId()),wrd.getGermanWord(),wrd.getEnglishWord()};
    String [] actual = {
            table.getRow(3).getValuesList().get(0).getValue().toString(),
            table.getRow(3).getValuesList().get(1).getValue().toString(),
            table.getRow(3).getValuesList().get(2).getValue().toString()
    };

    org.assertj.core.api.Assertions.assertThat(expected).isEqualTo(actual);

  }
  @Test
  @Order(5)
  void t0050_findById(){
    Table table = new Table(dataSource, "WORD");

    Word word = repository.findById(1L);
    output(table).toConsole();

    String [] expected = {String.valueOf(word.getId()), word.getGermanWord(), word.getEnglishWord()};
    String [] actual = {
            table.getRow(0).getValuesList().get(0).getValue().toString(),
            table.getRow(0).getValuesList().get(1).getValue().toString(),
            table.getRow(0).getValuesList().get(2).getValue().toString()
    };

    org.assertj.core.api.Assertions.assertThat(expected).isEqualTo(actual);
  }
}