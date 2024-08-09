package cl.praxis.bootcapp.services.imp;

import cl.praxis.bootcapp.entities.Subject;
import cl.praxis.bootcapp.repositories.ISubjectRepository;
import cl.praxis.bootcapp.services.IBaseServiceCRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class SubjectService implements  IBaseServiceCRUD<Subject> {
    @Autowired
    private ISubjectRepository subjectRepository;

    @Override
    public List<Subject> getAll()
    {
        return subjectRepository.findAll();
    }

    @Override
    public Subject getById(Long id) {
        return subjectRepository.findById(id).orElse(null);
    }

    @Override
    public Subject create(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public Subject update(Subject subject){
        return subjectRepository.save(subject);
    }

    @Override
    public boolean delete(Long id) {
        if(subjectRepository.existsById(id)) {
            subjectRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
