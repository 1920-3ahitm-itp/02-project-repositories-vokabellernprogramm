package at.htl.project.controller;

import at.htl.project.model.Word;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WordRepository implements Repository<Word> {

    private DataSource dataSource = Database.getDataSource();

    @Override
    public void save(Word newWord) {
        insert(newWord);
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List<Word> findAll() {
        List<Word> words = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {

            String sql = "SELECT WRD_ID, WRD_GERMAN, WRD_ENGLISH FROM WORD";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                int id = result.getInt("ID");
                String germanWord = result.getString("GERMAN_WORD");
                String englishWord = result.getString("ENGLISH_WORD");
                words.add(new Word(germanWord, englishWord));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return words;
    }

    @Override
    public Word findById(long id) {
        return null;
    }

    @Override
    public List<Word> findByName(String name) {
        return null;
    }

    //    public Word save(Word newWord) {
//        return insert(newWord);
//    }


//    public void createTable() {
//        Database db = new Database();
//        try (Connection connection = dataSource.getConnection()) {
//
//            String sql = "CREATE TABLE WORD(" +
//                    "id INT GENERATED ALWAYS AS IDENTITY " +
//                    "CONSTRAINT pk_ PRIMARY KEY, " +
//                    "GERMAN_WORD VARCHAR(100)," +
//                    "ENGLISH_WORD VARCHAR(100)" +
//                    ")";
//
//            System.out.println(sql);
//            PreparedStatement pstmt = connection.prepareStatement(sql);
//            pstmt.execute();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public void delete(String englishWord) {
        try (Connection connection = dataSource.getConnection()) {
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate("DELETE FROM WORD WHERE WRD_ENGLISH =" + englishWord);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Word insert(Word wordToSave) {

        try (Connection conn = dataSource.getConnection();
                PreparedStatement stmnt = conn.prepareStatement("INSERT INTO WORD (WRD_GERMAN, WRD_ENGLISH) " +
                        "values (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            stmnt.setString(1, wordToSave.getGermanWord());
            stmnt.setString(2, wordToSave.getEnglishWord());

            if (stmnt.executeUpdate() == 0) {
                throw new SQLException("Creating user failed, no rows affected");
            }

            /*try (ResultSet generatedKeys = stmnt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    wordToSave.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained");
                }
            }*/

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return wordToSave;
    }

    public void dropTable() {
        Database db = new Database();
        try (Connection connection = dataSource.getConnection()) {

            String sql = "DROP TABLE WORD";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();
            System.out.println("Dropped table WORD.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public boolean tableExists() {
//        Database db = new Database();
//        try (Connection connection = dataSource.getConnection()) {
//
//            String sql = "SELECT * FROM SYS.SYSTABLES WHERE TABLENAME = 'WORD'";
//            PreparedStatement pstmt = connection.prepareStatement(sql);
//            ResultSet result = pstmt.executeQuery();
//
//            if (result.next()) {
//                return true;
//            } else {
//                return false;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
   /* private List<model.Word> readCsv(String fileName, int numberOfLines) {
        List<model.Word> words = new LinkedList<>();

        List<String> lines = null;
        try {
            Files
                    .readAllLines(Paths.get(fileName), StandardCharsets.UTF_8)
                    .stream()
                    .skip(1)
                    .limit(numberOfLines)
                    .peek(System.out::println)
                    .map(line -> line.split(";"))
                    .map(elements -> new model.Word(elements[0], elements[1]))
                    //.distinct()
                    .forEach(words::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }*/

//    public List<Word> getAllWords() {
//        List<Word> words = new ArrayList<>();
//
//        try (Connection connection = dataSource.getConnection()) {
//
//            String sql = "SELECT W_C_ID, W_GERMAN, W_ENGLISH FROM WORD";
//            PreparedStatement statement = connection.prepareStatement(sql);
//            ResultSet result = statement.executeQuery();
//
//            while (result.next()) {
//                int id = result.getInt("ID");
//                String germanWord = result.getString("GERMAN_WORD");
//                String englishWord = result.getString("ENGLISH_WORD");
//                words.add(new Word(germanWord, englishWord));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return words;
//    }


}