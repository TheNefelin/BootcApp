package cl.praxis.bootcapp.services;

import cl.praxis.bootcapp.entities.Subject;

import java.util.List;

public interface ISubjectService {
    List<Subject> findAll();
    Subject findById(Long id);
    Subject save(Subject subject);
    Subject update(Subject subject);
    void delete (Subject subject);
}
