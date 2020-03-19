package at.htl.project.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventWord {

    //eventType: Art des Events
    EVENT eventType;

    //date: Datum wo dieses Event stattfindet zur Unterscheidung
    Date date;

    //words: Die Wörter des Events
    List<Word> words = new ArrayList<>();

    public EventWord(EVENT eventType, Date date, List<Word> words) {
        this.eventType = eventType;
        this.date = date;
        this.words = words;
    }

    //Getter und Setter
    public EVENT getEventType() {
        return eventType;
    }

    public void setEventType(EVENT eventType) {
        this.eventType = eventType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }
}
