package at.htl.vocabulary.controller;

import java.util.List;

public interface Repository<T> {
    public void save(T entity);
    public void delete(Long id);
    public List<T> findAll();
    public T findById(Long id);

    //List<T> findByName(String name);
}
