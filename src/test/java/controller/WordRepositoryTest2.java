package controller;

import model.Word;
import org.assertj.core.api.Assertions;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;

public class WordRepositoryTest2 {

    String TABLE_NAME = "WORD";
    WordRepository repository = new WordRepository();
    DataSource dataSource = Database.getDataSource();


    @Test
    void test020_insertWord(){

        // arrange
        Word word01 = new Word("Hund", "dog");

        // act
        repository.createTable();
        repository.save(word01);

        // assert
        Table table = new Table(dataSource, "word");
        output(table).toConsole();

        //assertThat(repository).isNotNull();
    }

}
