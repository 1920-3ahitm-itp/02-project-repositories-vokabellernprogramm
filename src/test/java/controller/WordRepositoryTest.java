package controller;

import model.Word;
import org.apache.derby.jdbc.ClientDataSource;
import org.assertj.core.api.Assertions;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import javax.xml.crypto.Data;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;
import static org.junit.jupiter.api.Assertions.*;

class WordRepositoryTest {

    String TABLE_NAME = "WORD";
    WordRepository repository;
    DataSource dataSource = Database.getDataSource();

    @BeforeEach
    void beforeEach() {
        repository = new WordRepository();

        if (repository.tableExists()) {
            repository.dropTable();
        }
//        repository.createTable();
    }

    @Test
    void createTable() {
        repository = new WordRepository();
        repository.createTable();

    }


   @Test
   void test020_insertWord(){
       Word word01 = new Word("Hund", "dog");
       repository.createTable();
       repository.save(word01);

       assertThat(repository).isNotNull();
   }

/*
   @Test
   void test030_deleteWord(){
       Word word01 = new Word("Hund", "dog");
       repository.save(word01);
       repository.delete(word01.getGermanWord());

       assertThat(repository).isNull();
   }*/

   @Test
   void test080_saveTwoWords() {
       Word word01 = new Word("Hund", "dog");
       Word word02 = new Word("Katze", "cat");

       WordRepository personRepository = new WordRepository();
       personRepository.createTable();
       personRepository.save(word01);
       personRepository.save(word02);

       Table wordTable = new Table(dataSource, TABLE_NAME);
       output(wordTable).toConsole();
       org.assertj.db.api.Assertions.assertThat(wordTable).hasNumberOfRows(2);
   }

   @Test
   void test090_dropTable() {

       // act
       repository.dropTable();

       // assert
       assertThat(repository.tableExists() == false);
   }
}