package at.htl.vocabulary.model;

public class Word {

    private Integer id;
    private String germanWord = "";
    private String englishWord = "";
    //private Category category = new Category();

    public Word() {
    }

    public Word(Integer id, String germanWord, String englishWord) {
        this.id = id;
        this.germanWord = germanWord;
        this.englishWord = englishWord;
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

    public Integer getId() {
        return id;
    }

    public String getGermanWord() {
        return germanWord;
    }

    public String getEnglishWord() {
        return englishWord;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGermanWord(String germanWord) {
        this.germanWord = germanWord;
    }

    public void setEnglishWord(String englishWord) {
        this.englishWord = englishWord;
    }

    @Override
    public String toString() {
        return String.format("g: %s - e: %s", germanWord, englishWord);
    }
}