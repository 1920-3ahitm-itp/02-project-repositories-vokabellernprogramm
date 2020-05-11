package at.htl.vocabulary.controller;

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
  void save(){
      Word word = new Word(0, "Schule", "school");
      repository.save(word);


      Table table = new Table(Database.getDataSource(), "WORD");

      Assertions.assertThat(table).row(table.getRowsList().size() - 1)
              .value("W_GERMAN").isEqualTo("Schule")
              .value("W_ENGLISH").isEqualTo("school");
  }

    @Test
  void createTable() {
//    repository.createTable();
//
//    assertThat(repository.tableExists() == true);
  }

  @Test
  void delete() {

  }

  @Test
  void dropTable() {
  }

  @Test
  void tableExists() {
     // assertThat(repository.tableExists() == true);
  }

  @Test
  void getAllWords() {

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