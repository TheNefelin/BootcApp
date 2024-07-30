package cl.praxis.bootcapp.services;

import cl.praxis.bootcapp.entities.Note;
import cl.praxis.bootcapp.entities.NoteDTO;

import java.util.List;

public interface INoteService {
    List<NoteDTO> getNoteByIdSubject(Long idSubject);
    List<NoteDTO> getNoteByIdTeacher(Long idTeacher);
    List<NoteDTO> getNoteByIdStudent(Long idStudent);
}
