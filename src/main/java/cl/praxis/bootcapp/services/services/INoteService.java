package cl.praxis.bootcapp.services.services;

import cl.praxis.bootcapp.entities.GradeDTO;

import java.util.List;

public interface INoteService {
    List<GradeDTO> getNoteByIdSubject(Long idSubject);
    List<GradeDTO> getNoteByIdTeacher(Long idTeacher);
    List<GradeDTO> getNoteByIdStudent(Long idStudent);
}
