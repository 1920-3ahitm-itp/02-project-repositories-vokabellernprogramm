package at.htl.vocabulary.controller;

import at.htl.vocabulary.model.Category;
import at.htl.vocabulary.model.Word;
import org.assertj.db.api.Assertions;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;

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

//    Word wrd = new Word("Schule","school");
//    repository.save(wrd);
//
//    Table table = new Table(dataSource, "WORD");
//    Assertions.assertThat(table).row(table.getRowsList().size() - 1)
//            .value("WRD_ENGLISH").isEqualTo("school");
  }

  @Test
  void t0020_delete() {


  }


  @Test
  void t0030_findAll() {
//
//    int allRows = repository.findAll().size();
//    Table table = new Table(dataSource, "WORD");
//    int tableRows = table.getRowsList().size();
//
//    org.assertj.core.api.Assertions.assertThat(allRows).isEqualTo(tableRows);
  }

  @Test
  void t0040_findByName(){

  }
  @Test
  void t0050_findById(){

  }

  @Test
  void t0060_getAllWords() {

//    List<Word> words = repository.getAllWords();
//    Table table = new Table(Database.getDataSource(), "WORD");
//    List<Word> expected = new ArrayList<>();
//
//    for(Row row: table.getRowsList()){
//      int id = (int) row.getColumnValue("W_ID").getValue();
//      String germanWord = (String) row.getColumnValue("W_GERMAN").getValue();
//      String englishword = (String) row.getColumnValue("W_ENGLISH").getValue();
//      Word word = new Word(id, germanWord, englishword);
//      expected.add(word);
//    }
//
//    assertThat(words).containsExactly(expected.toArray(new Word[0]));
  }
}