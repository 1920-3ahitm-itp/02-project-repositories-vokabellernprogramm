package at.htl.vocabulary.controller;

import at.htl.vocabulary.model.Category;
import at.htl.vocabulary.model.Word;
import org.assertj.db.api.Assertions;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;
import static org.junit.jupiter.api.Assertions.fail;

class WordRepositoryTest {

  String TABLE_NAME = "WORD";
  String TABLE_NAME2 = "EVENT";
  DataSource dataSource = Database.getDataSource();
  WordRepository repository = new WordRepository();

//  @BeforeEach
//  void beforeEach() {
//    repository = new WordRepository();
//
//    if (repository.tableExists()) {
//      repository.dropTable();
//    }
//  }



  @Test
  void t0010_save(){

    Word wrd = new Word("Schule","school");
    Table table = new Table(dataSource, "WORD");
    output(table).toConsole();
    repository.save(wrd);
    output(table).toConsole();


    Assertions.assertThat(table).row(table.getRowsList().size() - 1)
            .value("WRD_ENGLISH").isEqualTo("school");
  }

  @Test
  void t0020_delete() {
//    String englishWrd = "School";
//
//    Table table = new Table(dataSource, "WORD");
//    output(table).toConsole();
//    int expectedRows = table.getRowsList().size() - 1;
//    repository.deleteByEnglishWrd(englishWrd);
//
//    table = new Table(dataSource, "WORD");
//    output(table).toConsole();
//    Assertions.assertThat(table).hasNumberOfRows(expectedRows);
//
//    if (repository.findByName(englishWrd) != null) {
//      fail("CATEGORY " + englishWrd + " not deleted from db");
//    }
  }


  @Test
  void t0030_findAll() {

    int allRows = repository.findAll().size();
    Table table = new Table(dataSource, "WORD");
    int tableRows = table.getRowsList().size();

    org.assertj.core.api.Assertions.assertThat(allRows).isEqualTo(tableRows);
  }

  @Test
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
  void t0050_findById(){
    Table table = new Table(dataSource, "WORD");

    Word word = repository.findById(1);
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