package cl.praxis.bootcapp.services;

<<<<<<< HEAD
import cl.praxis.bootcapp.entities.Grade;
=======
>>>>>>> develop
import cl.praxis.bootcapp.entities.GradeDTO;

import java.util.List;

public interface IGradeService {
<<<<<<< HEAD
    List<GradeDTO> getGradeByIdSubject(Long idSubject);
    List<GradeDTO> getGradeByIdTeacher(Long idTeacher);
    List<GradeDTO> getGradeByIdStudent(Long idStudent);

    Grade create(Grade grade);
=======
    // obtiene Notas con los OBJ Teacher, Student, Subject en una listaDTO por idSubject
    List<GradeDTO> getAllNoteByIdSubject(Long idSubject);

    // obtiene Notas con los OBJ Teacher, Student, Subject en una listaDTO por idTeacher
    List<GradeDTO> getAllNoteByIdTeacher(Long idTeacher);

    // obtiene Notas con los OBJ Teacher, Student, Subject en una listaDTO por idStudent
    List<GradeDTO> getAllNoteByIdStudent(Long idStudent);

    // obtiene Notas con los OBJ Teacher, Student, Subject en una listaDTO
    List<GradeDTO> getAllNote();

    // obtiene Notas con los OBJ Teacher, Student, Subject en un DTO por idNote
    GradeDTO getNoteByIdNote(Long idNote);
>>>>>>> develop
}
