package cl.praxis.bootcapp.controllers.rests;

import cl.praxis.bootcapp.entities.Subject;
import cl.praxis.bootcapp.services.IBaseServiceCRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
public class SubjectRestController {

    @Autowired
    private IBaseServiceCRUD<Subject> subjectService;

    @GetMapping
    public List<Subject> getAllSubject(){
        return  subjectService.getAll();
    }

    @GetMapping("/{id}")
    public Subject getSubjectById(@PathVariable Long id){
        return subjectService.getById(id);
    }

    @PostMapping
    public Subject createSubject(@RequestBody Subject subject) {
        return  subjectService.create(subject);
    }

    @PutMapping("/{id}")
    public Subject updateSubject(@PathVariable Long id, @RequestBody Subject subject) {
        subject.setId(id);
        return  subjectService.update(subject);
    }

    @DeleteMapping("/{id}")
    public boolean deleteSubject(@PathVariable Long id) {
        return  subjectService.delete(id);
    }
}
