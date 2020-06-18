package at.htl.vocabulary.database;

import at.htl.vocabulary.controller.Database;

import javax.sql.DataSource;
import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.ibatis.jdbc.ScriptRunner;

public class SqlRunner {

  private static final String SCRIPT_PROPERTIES_PATH = "sql/script-files.properties";

  public static void main(String[] args) {
    runScript();
  }

  public static void runScript() {
    try {
      Properties scriptProperties = new Properties();
      scriptProperties.load(new FileInputStream(SCRIPT_PROPERTIES_PATH));


      DataSource dataSource = Database.getDataSource();
      Connection conn = dataSource.getConnection();
      System.out.println("Connection established......");
      ScriptRunner sr = new ScriptRunner(conn);

      for (String file : scriptProperties.stringPropertyNames()) {
        Reader reader = new BufferedReader(new FileReader(scriptProperties.getProperty(file)));
        sr.runScript(reader);
      }

    } catch (SQLException | IOException e) {
      e.printStackTrace();
    }
  }
}
