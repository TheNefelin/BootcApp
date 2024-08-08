package cl.praxis.bootcapp.services;

import cl.praxis.bootcapp.entities.GradeDTO;

import java.util.List;

public interface IGradeService {
    List<GradeDTO> getAllNotesByIdSubject(Long idSubject);
    List<GradeDTO> getAllNotesByIdTeacher(Long idTeacher);
    List<GradeDTO> getAllNotesByIdStudent(Long idStudent);
    List<GradeDTO> getAllNotes();
    GradeDTO getNotesByIdNote(Long idNote);

}