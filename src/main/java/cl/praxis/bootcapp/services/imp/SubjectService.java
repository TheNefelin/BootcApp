package cl.praxis.bootcapp.services.imp;

import cl.praxis.bootcapp.entities.GradeDTO;
import cl.praxis.bootcapp.entities.Subject;
import cl.praxis.bootcapp.repositories.ICourseRepository;
import cl.praxis.bootcapp.repositories.ISubjectRepository;
import cl.praxis.bootcapp.services.IBaseServiceCRUD;
import cl.praxis.bootcapp.services.IGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class SubjectService implements  IBaseServiceCRUD<Subject> {
    @Autowired
    private ISubjectRepository subjectRepository;

    @Autowired
    private IGradeService gradeService;

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
        List<GradeDTO> gradesDTO = gradeService.getAllGradesByIdSubject(id);
        if(gradesDTO.isEmpty()) {
            subjectRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
