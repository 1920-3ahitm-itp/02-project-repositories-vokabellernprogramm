package at.htl.project.database;

import at.htl.project.controller.Database;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.jdbc.ScriptRunner;

public class SqlRunner {

  private static final List<String> sqlFilePaths =
      List.of(
          "sql/dr_vokabel.sql",
          "sql/cr_vokabel.sql",
          "sql/ins_vokabel.sql"
      );

  public static void main(String[] args) {
    DataSource dataSource = Database.getDataSource();
    Connection conn;

    try {
      conn = dataSource.getConnection();
      System.out.println("Connection established......");
      ScriptRunner sr = new ScriptRunner(conn);

      for (String file : sqlFilePaths) {
        Reader reader = new BufferedReader(new FileReader(file));
        sr.runScript(reader);
      }

    } catch (SQLException | FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}
