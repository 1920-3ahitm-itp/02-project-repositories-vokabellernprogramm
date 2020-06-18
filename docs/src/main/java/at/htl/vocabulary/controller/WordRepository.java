package at.htl.vocabulary.controller;

import at.htl.vocabulary.model.Category;
import at.htl.vocabulary.model.Word;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WordRepository implements Repository<Word> {

    private DataSource dataSource = Database.getDataSource();

    @Override
    public void save(Word newWord) {
        if (newWord.getId() == null){
            insert(newWord);
        }else{
            update(newWord);
        }
    }

    private void update(Word newWord) {
            try (Connection connection = dataSource.getConnection()) {
                String sql = "UPDATE word SET wrd_german=?, wrd_english=? WHERE wrd_id=?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setLong(1, newWord.getId());
                statement.setString(2, newWord.getGermanWord());
                statement.setString(3, newWord.getEnglishWord());

                if (statement.executeUpdate() == 0) {
                    throw new SQLException("Update of WORD failed, no rows affected");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "DELETE FROM word WHERE wrd_id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);

            if (statement.executeUpdate() == 0) {
                throw new SQLException("Delete from WORD failed, no rows affected");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteByEnglishWrd(String englishWrdName) {
        Word toDelete = findByName(englishWrdName);
        delete(toDelete.getId());
    }

    @Override
    public List<Word> findAll() {
        List<Word> words = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {

            String sql = "SELECT wrd_id, wrd_german, wrd_english FROM word";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                int id = result.getInt("WRD_ID");
                String germanWord = result.getString("WRD_GERMAN");
                String englishWord = result.getString("WRD_ENGLISH");
                words.add(new Word(germanWord, englishWord));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return words;
    }

    @Override
    public Word findById(Long id) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM word WHERE wrd_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                Word selectedWord = new Word();
                selectedWord.setId((long) Math.toIntExact(id));
                resultSet.next();
                String germanWrd = resultSet.getString("WRD_GERMAN");
                String englishWrd = resultSet.getString("WRD_ENGLISH");
                selectedWord.setGermanWord(germanWrd);
                selectedWord.setEnglishWord(englishWrd);
                return selectedWord;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
//
//
//    public void deleteByWrd(String englishWord) {
//        try (Connection connection = dataSource.getConnection()) {
//            try (Statement statement = connection.createStatement()) {
//                statement.executeUpdate("DELETE FROM word WHERE wrd_english =" + englishWord);
//
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }


    public Word findByName(String name) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT wrd_id, wrd_german, wrd_english FROM word WHERE wrd_english = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                Long id = resultSet.getLong(1);
                String germanWrd = resultSet.getString(2);
                String englishWrd = resultSet.getString(3);
                return new Word(id, germanWrd, englishWrd);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    private Word insert(Word wordToSave) {

        try (Connection conn = dataSource.getConnection();
                PreparedStatement stmnt = conn.prepareStatement("INSERT INTO word (wrd_german, wrd_english) " +
                        "values (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            stmnt.setString(1, wordToSave.getGermanWord());
            stmnt.setString(2, wordToSave.getEnglishWord());

            if (stmnt.executeUpdate() == 0) {
                throw new SQLException("Creating user failed, no rows affected");
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return wordToSave;
    }

//    public void dropTable() {
//        Database db = new Database();
//        try (Connection connection = dataSource.getConnection()) {
//
//            String sql = "DROP TABLE WORD";
//
//            PreparedStatement statement = connection.prepareStatement(sql);
//            statement.execute();
//            System.out.println("Dropped table WORD.");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

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
//
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