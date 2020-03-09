package model;

public class Word {

    private String germanWord = "";
    private String englishWord = "";

    public Word() {
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