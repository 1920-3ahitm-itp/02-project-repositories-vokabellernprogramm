package at.htl.vocabulary.controller;

import at.htl.vocabulary.model.Category;
import at.htl.vocabulary.model.Event;
import at.htl.vocabulary.model.EventType;

import javax.sql.DataSource;
import java.net.Inet4Address;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EventRepository implements Repository<Event> {

    private final DataSource dataSource = Database.getDataSource();

    @Override
    public void save(Event event) {
        if (event.getId() == null) {
            create(event);
        } else {
            update(event);
        }
    }

    private int create(Event event) {
        int generatedKey = 0;
        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO event (evt_event_type, evt_date, evt_descr) VALUES (?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, String.valueOf(event.getEventType()));
            statement.setDate(2, Date.valueOf(event.getDate()));
            statement.setString(3, event.getEventDescription());

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
            String sql = "UPDATE event SET evt_event_type=?, evt_date=?, evt_descr=? WHERE evt_id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, event.getId());
          // ?
            statement.setString(2, String.valueOf(event.getEventType()));
            statement.setDate(3, Date.valueOf(event.getDate()));
            statement.setString(4, event.getEventDescription());

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
            String sql = "SELECT evt_id, evt_event_type, evt_date, evt_descr FROM event";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                int id = result.getInt("EVT_ID");
               // int eventTypeId = result.getInt("EVT_ET_ID");
                Date date = result.getDate("EVT_DATE");
                String description = result.getString("EVT_DESCR");
              //  EventType eventType = EventType.valueOf(description.toUpperCase());
                EventType eventType = EventType.valueOf(result.getString("EVT_EVENT_TYPE"));
                events.add(new Event(id, eventType, date.toLocalDate(), description));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }

    @Override
    public Event findById(int id) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT evt_id, evt_event_type, evt_date, evt_descr FROM event WHERE evt_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                Event selectedEvent = new Event();
                selectedEvent.setEvtId(id);
                resultSet.next();
                EventType eventType = EventType.valueOf(resultSet.getString("EVT_EVENT_TYPE"));
                Date date = resultSet.getDate("EVT_DATE");
                String description = resultSet.getString("EVT_DESCR");
                selectedEvent.setEventType(eventType);
                selectedEvent.setDate(date.toLocalDate());
                selectedEvent.setEventDescription(description);
                return selectedEvent;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

