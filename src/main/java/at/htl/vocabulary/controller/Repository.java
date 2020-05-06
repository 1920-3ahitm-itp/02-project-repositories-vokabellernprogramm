package at.htl.vocabulary.controller;

import java.util.List;

public interface Repository<T> {
    public void save(T entity);
    public void delete(int id);
    public List<T> findAll();
    public T findById(int id);

    //List<T> findByName(String name);
}
