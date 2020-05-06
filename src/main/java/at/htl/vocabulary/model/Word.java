package at.htl.vocabulary.model;

public class Word {

    private int id;
    private String germanWord = "";
    private String englishWord = "";
    //private Category category = new Category();

    public Word() {
    }

    public Word(int id, String germanWord, String englishWord) {
        this.id = id;
        this.germanWord = germanWord;
        this.englishWord = englishWord;
    }

    public Word(String germanWord, String englishWord) {
        this.germanWord = germanWord;
        this.englishWord = englishWord;
    }

    public String getGermanWord() {
        return germanWord;
    }

    public String getEnglishWord() {
        return englishWord;
    }

    @Override
    public String toString() {
        return String.format("g: %s - e: %s", germanWord, englishWord);
    }
}