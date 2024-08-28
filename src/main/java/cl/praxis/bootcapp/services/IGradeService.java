package cl.praxis.bootcapp.services;

import cl.praxis.bootcapp.entities.dtos.GradeDTO;

import java.util.List;

public interface IGradeService {

    List<GradeDTO> getAllGradesByIdSubject(Long idSubject);
    List<GradeDTO> getAllGradesByIdTeacher(Long idTeacher);
    List<GradeDTO> getAllGradesByIdStudent(Long idStudent);
    List<GradeDTO> getAllGrades();
    GradeDTO getGradesByIdGrade(Long idNote);

}

