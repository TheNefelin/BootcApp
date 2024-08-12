package cl.praxis.bootcapp.controllers;


import cl.praxis.bootcapp.entities.Grade;
import cl.praxis.bootcapp.entities.GradeDTO;
import cl.praxis.bootcapp.services.IBaseServiceCRUD;
import cl.praxis.bootcapp.services.IGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grades")
public class GradeRestController {

    @Autowired
    private IGradeService gradeService;

    @Autowired
    private IBaseServiceCRUD<Grade> gradeServiceCrud;


    //---------------------------CRUD------------------------------------------//
    @GetMapping
    public List<GradeDTO> getAllGrades(){
        return gradeService.getAllGrades();
    }

    @GetMapping("/{id}")
    public GradeDTO getGradeByIdGrade(@PathVariable Long id){
        return gradeService.getGradesByIdGrade(id);
    }

    @PostMapping
    public Grade createGrade(@RequestBody Grade grade) {
        return gradeServiceCrud.create(grade);
    }

    @PutMapping("/{id}")
    public Grade updateGrade(@PathVariable Long id, @RequestBody Grade grade) {
        grade.setId(id);
        return  gradeServiceCrud.update(grade);
    }

    @DeleteMapping("/{id}")
    public boolean deleteGrade(@PathVariable Long id) {
        return gradeServiceCrud.delete(id);
    }

    //--------------------POR ENTIDAD------------------------------------------------//

    @GetMapping("/subject/{idSubject}")
    public List<GradeDTO> getAllGradesByIdSubject(@PathVariable Long idSubject) {
        return gradeService.getAllGradesByIdSubject(idSubject);
    }

    @GetMapping("/teacher/{idTeacher}")
    public List<GradeDTO> getAllGradesByIdTeacher(@PathVariable Long idTeacher) {
        return gradeService.getAllGradesByIdTeacher(idTeacher);
    }

    @GetMapping("/student/{idStudent}")
    public List<GradeDTO> getAllGradesByIdStudent(@PathVariable Long idStudent) {
        return gradeService.getAllGradesByIdStudent(idStudent);
    }














}
