package at.htl.project.model;

import at.htl.project.model.Word;

import java.util.ArrayList;
import java.util.List;

public class Category {

    private Long id;
    private String name;
    private List<Word> words = new ArrayList<>();

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("%d: %s", id, name);
    }
}
