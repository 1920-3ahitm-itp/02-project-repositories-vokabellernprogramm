package controller;

import org.apache.derby.jdbc.ClientDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Database {

    static final String DATABASE = "db";
    static final String USERNAME = "app";
    static final String PASSWORD = "app";
    public static final String URL = "jdbc:derby://localhost:1527/" + DATABASE + ";create=true";


    static DataSource getDataSource(){
        ClientDataSource dataSource = new ClientDataSource();
        dataSource.setDatabaseName(DATABASE);
        dataSource.setUser(USERNAME);
        dataSource.setPassword(PASSWORD);
        return dataSource;
    }

    public void createTableTest(){
        try (Connection conn = getDataSource().getConnection()) {
            String sql = "create table word (" +
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