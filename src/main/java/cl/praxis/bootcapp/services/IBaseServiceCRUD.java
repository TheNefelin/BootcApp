package cl.praxis.bootcapp.services;

import cl.praxis.bootcapp.entities.Subject;

import java.util.List;

public interface IBaseServiceCRUD<T> {
    List<T> getAll();
    T getById(Long id);
    T create(T t);
    T update(T t);
    boolean delete(Long id);
}
