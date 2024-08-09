package cl.praxis.bootcapp.services.imp;

import cl.praxis.bootcapp.entities.Subject;
import cl.praxis.bootcapp.repositories.ISubjectRepository;
import cl.praxis.bootcapp.services.IBaseServiceCRUD;
import cl.praxis.bootcapp.services.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class SubjectService implements ISubjectService, IBaseServiceCRUD<Subject> {
    @Autowired
    private ISubjectRepository subjectRepository;

    @Override
    public List<Subject> findAll(){
        return subjectRepository.findAll();
    }

   @Override
    public Subject findById(Long id){

       return subjectRepository.findById(id).orElse(null);
   }

    @Override
    public Subject save(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public List<Subject> getAll() {
        return null;
    }

    @Override
    public Subject getById(int id) {
        return null;
    }

    @Override
    public Subject create(Subject subject) {
        return null;
    }

    @Override
    public Subject update(Subject subject){
        return subjectRepository.update(subject);

    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public void delete(Subject subject) {
        subjectRepository.delete(subject);

    }
}
