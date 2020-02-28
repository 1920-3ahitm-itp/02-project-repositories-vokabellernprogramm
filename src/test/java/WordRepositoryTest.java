import org.apache.derby.jdbc.ClientDataSource;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;

import org.assertj.core.data.Offset;
import org.assertj.db.type.Table;

public class WordRepositoryTest {
    String TABLE_NAME = "word";
    WordRepository wordRepository = new WordRepository();
    ClientDataSource dataSource = new ClientDataSource();

    @Test
    void test070_insertRecord() {

        Word word1 = new Word("Katze", "cat");

        WordRepository wordRepository = WordRepository.getInstance();
        Word savedWord = wordRepository.save(word1);

        Table wordTable = new Table(dataSource, TABLE_NAME);
        output(wordTable).toConsole();
        org.assertj.db.api.Assertions.assertThat(wordTable).hasNumberOfRows(1);
        Assertions.assertThat(word1).isEqualTo(savedWord);
        Assertions.assertThat(savedWord.getEnglishWord()).isEqualTo("cat");
    }


    @Test
    void test080_saveTwoWords() {
        Word word01 = new Word("Hund", "dog");
        Word word02 = new Word("Katze", "cat");

        WordRepository personRepository = WordRepository.getInstance();
        personRepository.createTable();
        personRepository.save(word01);
        personRepository.save(word02);

        Table personTable = new Table(dataSource, TABLE_NAME);
        output(personTable).toConsole();
        org.assertj.db.api.Assertions.assertThat(personTable).hasNumberOfRows(2);
    }
}


