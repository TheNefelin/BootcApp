package cl.praxis.bootcapp.services;

import cl.praxis.bootcapp.entities.GradeDTO;

import java.util.List;

public interface IGradeService {
    List<GradeDTO> getAllNoteByIdSubject(Long idSubject);
    List<GradeDTO> getAllNoteByIdTeacher(Long idTeacher);
    List<GradeDTO> getAllNoteByIdStudent(Long idStudent);
    List<GradeDTO> getAllNote();
    GradeDTO getNoteByIdNote(Long idNote);
}
