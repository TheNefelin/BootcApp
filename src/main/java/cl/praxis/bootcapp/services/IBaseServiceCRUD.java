package cl.praxis.bootcapp.services;

import cl.praxis.bootcapp.entities.GradeDTO;

import java.util.List;

public interface IBaseServiceCRUD<T> {
    // obtiene Notas con los OBJ Teacher, Student, Subject en una listaDTO
    List<GradeDTO> getAllGrade();

    List<T> getAll();
    T getById(Long id);
    T create(T t);
    T update(T t);
    boolean delete(Long id);
}
