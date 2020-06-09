package at.htl.vocabulary.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Event {

    private Long evtId;

    //eventType: Art des Events
    private EventType eventType;

    //date: Datum wo dieses Event stattfindet zur Unterscheidung
    private LocalDate date;

    private String eventDescription;

    //words: Die Wörter des Events
    private List<Word> words = new ArrayList<>();

    public Event() {
    }

    public Event(Long evtId, EventType eventType, LocalDate date, String eventDescription) {
        this.evtId = evtId;
        this.eventType = eventType;
        this.date = date;
        this.eventDescription = eventDescription;
    }

    public Event(EventType eventType, LocalDate date, String eventDescription) {
        this.evtId = null;
        this.eventType = eventType;
        this.date = date;
        this.eventDescription = eventDescription;
    }

    //Getter und Setter
    public Long getId() {
        return evtId;
    }

    public void setEvtId(Long evtId) {
        this.evtId = evtId;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Word> getWords() { return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }
}
