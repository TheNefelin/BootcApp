package cl.praxis.bootcapp.services;

import cl.praxis.bootcapp.entities.GradeDTO;

import java.util.List;

public interface IGradeService {
    List<GradeDTO> getAllGradeByIdSubject(Long idSubject);
    List<GradeDTO> getAllGradeByIdTeacher(Long idTeacher);
    List<GradeDTO> getAllGradeByIdStudent(Long idStudent);
    List<GradeDTO> getAllGrade();
    GradeDTO getGradeByIdGrade(Long idGrade);
}
