import org.apache.derby.jdbc.ClientDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Database {

    static final String db = "db";
    static final String username = "app";
    static final String password = "app";

    public DataSource getDataSource(){
        ClientDataSource dataSource = new ClientDataSource();
        dataSource.setDatabaseName(db);
        dataSource.setUser(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    public void createTableTest(){
        try (Connection conn = getDataSource().getConnection()) {
            String sql = "create table vocabulary (" +
                    "id int primary key," +
                    "german_word varchar(100)," +
                    "english_word varchar(100)" +
                    ")";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}