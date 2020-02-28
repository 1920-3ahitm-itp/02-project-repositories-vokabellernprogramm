import model.Word;

public interface Repository {
    public Word save(Word w);
    public void delete(String englishWord);
}
