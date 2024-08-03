package cl.praxis.bootcapp.services;

import cl.praxis.bootcapp.entities.Grade;

import java.util.List;

public interface IBaseServiceCRUD<T> {
    List<T> getAll();
    T getById(Long id);
    T create(T t);
    T update(T t);

    Grade update(Grade grade);

    boolean delete(Long id);
}
