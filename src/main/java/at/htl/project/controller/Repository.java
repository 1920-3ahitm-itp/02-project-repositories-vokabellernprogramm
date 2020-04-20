package at.htl.project.controller;

import at.htl.project.model.Category;

import java.util.List;

public interface Repository<T> {
    public void save(T entity);
    public void delete(long id);
    public List<T> findAll();
    public T findById(long id);

    List<T> findByName(String name);
}
