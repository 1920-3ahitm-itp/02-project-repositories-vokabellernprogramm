package at.htl.vocabulary.controller;

import java.util.List;

public interface Repository<T> {
    public void save(T entity);
    public void delete(long id);
    public List<T> findAll();
    public T findById(long id);

    List<T> findByName(String name);
}
