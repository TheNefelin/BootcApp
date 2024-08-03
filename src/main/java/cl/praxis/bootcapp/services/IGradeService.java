package cl.praxis.bootcapp.services;

import cl.praxis.bootcapp.entities.Grade;
import cl.praxis.bootcapp.entities.GradeDTO;

import java.util.List;

public interface IGradeService {
    List<GradeDTO> getGradeByIdSubject(Long idSubject);
    List<GradeDTO> getGradeByIdTeacher(Long idTeacher);
    List<GradeDTO> getGradeByIdStudent(Long idStudent);

    Grade create(Grade grade);
}
