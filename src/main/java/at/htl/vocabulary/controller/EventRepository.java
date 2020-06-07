package at.htl.vocabulary.controller;

import at.htl.vocabulary.model.Category;
import at.htl.vocabulary.model.Event;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventRepository implements Repository<Event> {

    private final DataSource dataSource = Database.getDataSource();

    @Override
    public void save(Event event) {
        if (event.getId() == null) {
            create(event);
        }  else {
            update(event);

        }
    }

    private int create(Event event) {
        int generatedKey = 0;
        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO event (evt_date, evt_descr) VALUES (?,?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setDate(1, (Date) event.getDate());
            statement.setString(2, event.getEventDescription());

            if (statement.executeUpdate() == 0) {
                throw new SQLException("Insert of EVENT failed, no rows affected");
            }

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                generatedKey = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return generatedKey;
    }

    private void update(Event event) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "UPDATE event SET evt_date=?, evt_descr=? WHERE evt_id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, event.getId());
            statement.setDate(2, (Date) event.getDate());
            statement.setString(3, event.getEventDescription());

            if (statement.executeUpdate() == 0) {
                throw new SQLException("Update of EVENT failed, no rows affected");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "DELETE FROM event WHERE evt_id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            if (statement.executeUpdate() == 0) {
                throw new SQLException("Delete from EVENT failed, no rows affected");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Event> findAll() {
        List<Event> events = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT evt_id, evt_date, evt_descr FROM event";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Long id = result.getLong("EVT_ID");
                Date date = result.getDate("EVT_DATE");
                String description = result.getString("EVT_DESCR");
                events.add(new Event(id,date,description));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }

    @Override
    public Event findById(int id) {
        return null;
    }
}
