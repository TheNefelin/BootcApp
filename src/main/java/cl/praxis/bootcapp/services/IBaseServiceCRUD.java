package cl.praxis.bootcapp.services;

import java.util.List;

public interface IBaseServiceCRUD<T> {
    List<T> getAll();
    T getById(int id);
    T create(T t);
    T update(T t);
    boolean delete(int id);
}
