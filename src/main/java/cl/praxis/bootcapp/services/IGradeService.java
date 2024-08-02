package cl.praxis.bootcapp.services;

import cl.praxis.bootcapp.entities.GradeDTO;

import java.util.List;

public interface IGradeService {
    List<GradeDTO> getNoteByIdSubject(Long idSubject);
    List<GradeDTO> getNoteByIdTeacher(Long idTeacher);
    List<GradeDTO> getNoteByIdStudent(Long idStudent);
}
