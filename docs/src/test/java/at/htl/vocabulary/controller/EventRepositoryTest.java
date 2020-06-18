package at.htl.vocabulary.controller;

import at.htl.vocabulary.database.SqlRunner;
import at.htl.vocabulary.model.Category;
import at.htl.vocabulary.model.Event;
import at.htl.vocabulary.model.EventType;
import org.assertj.db.api.Assertions;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;

import java.sql.Date;
import java.time.LocalDate;

import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;
import static org.junit.jupiter.api.Assertions.*;

class EventRepositoryTest {

    DataSource dataSource = Database.getDataSource();
    static EventRepository repository;

    @BeforeAll
    static void beforeAll() {
        SqlRunner.runScript();
        repository = new EventRepository();
    }

    @Test
    void save() {
        Event englishSA2 = new Event(EventType.SCHULARBEIT, LocalDate.of(2020,06,14),"easy cheesy");

        Table table = new Table(dataSource, "EVENT");
        repository.save(englishSA2);

        // Prüfen mit assertJ, ob der Inhalt in DB ok ist
        Assertions.assertThat(table).row(table.getRowsList().size() - 1)
                .value("EVT_DESCR").isEqualTo("easy cheesy");

    }

    @Test
    void delete() {
        Event englishSA2 = new Event(EventType.SCHULARBEIT, LocalDate.of(2020,06,14),"easy cheesy");

        Table table = new Table(dataSource, "EVENT");
        int expectedRows = table.getRowsList().size() - 1;
        output(table).toConsole();

        repository.delete(4L);

        table = new Table(dataSource, "EVENT");
        output(table).toConsole();
        assertThat(table).hasNumberOfRows(expectedRows);

    }

    @Test
    void findAll() {
        int allRows = repository.findAll().size();
        Table table = new Table(dataSource, "EVENT");
        int tableRows = table.getRowsList().size();

        org.assertj.core.api.Assertions.assertThat(allRows).isEqualTo(tableRows);
    }

    @Test
    void findById() {

        Table table = new Table(dataSource, "EVENT");

        Event event = repository.findById(2L);
        output(table).toConsole();

        String [] expected = {String.valueOf(event.getId()),String.valueOf(event.getEventType()),
               String.valueOf(event.getDate()), event.getEventDescription()};

        String [] actual = {
                table.getRow(1).getValuesList().get(0).getValue().toString(),
                table.getRow(1).getValuesList().get(1).getValue().toString(),
                table.getRow(1).getValuesList().get(2).getValue().toString(),
                table.getRow(1).getValuesList().get(3).getValue().toString()
        };

        org.assertj.core.api.Assertions.assertThat(expected).isEqualTo(actual);

    }
}