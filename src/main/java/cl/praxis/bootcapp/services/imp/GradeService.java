package cl.praxis.bootcapp.services.imp;

import cl.praxis.bootcapp.entities.*;
import cl.praxis.bootcapp.repositories.IGradeRepository;
import cl.praxis.bootcapp.repositories.ISubjectRepository;
import cl.praxis.bootcapp.repositories.IUserRepository;
import cl.praxis.bootcapp.services.IBaseServiceCRUD;
import cl.praxis.bootcapp.services.IGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GradeService implements IGradeService, IBaseServiceCRUD<Grade> {
    @Autowired
    private IGradeRepository repoNote;

    // MODIFICAR
    @Autowired
    private ISubjectRepository repoSubject;

    // MODIFICAR
    @Autowired
    private IUserRepository repoUser;

    // obtiene Notas con los OBJ Teacher, Student, Subject en una listaDTO por idSubject
    @Override
    public List<GradeDTO> getAllNoteByIdSubject(Long idSubject) {
        List<Grade> grades = repoNote.findAllNotesByIdSubject(idSubject);
        return toDTO(grades);
    }

    // obtiene Notas con los OBJ Teacher, Student, Subject en una listaDTO por idTeacher
    @Override
    public List<GradeDTO> getAllNoteByIdTeacher(Long idTeacher) {
        List<Grade> grades = repoNote.findAllNotesByIdTeacher(idTeacher);
        return toDTO(grades);
    }

    // obtiene Notas con los OBJ Teacher, Student, Subject en una listaDTO por idStudent
    @Override
    public List<GradeDTO> getAllNoteByIdStudent(Long idStudent) {
        List<Grade> grades = repoNote.findAllNotesByIdStudent(idStudent);
        return toDTO(grades);
    }

    // obtiene Notas con los OBJ Teacher, Student, Subject en un DTO por idNote
    @Override
    public GradeDTO getNoteByIdNote(Long idNote) {
        Grade grade = repoNote.findById(idNote).orElse(null);
        List<Grade> grades = new ArrayList<>();
        List<GradeDTO> gradesDTO = new ArrayList<>();

        if (grade != null) {
            grades.add(grade);
            gradesDTO = toDTO(grades);
        } else {
            gradesDTO.add(null);
        }

        return gradesDTO.getFirst();
    }

    private List<GradeDTO> toDTO(List<Grade> grades) {
        Set<Long> idSubjectSet = grades.stream().map(Grade::getIdSubject).collect(Collectors.toSet());
        Set<Long> idTeacherSet = grades.stream().map(Grade::getIdTeacher).collect(Collectors.toSet());
        Set<Long> idStudentsSet = grades.stream().map(Grade::getIdStudent).collect(Collectors.toSet());

        Map<Long, Subject> subjectList = repoSubject.findAllById(idSubjectSet).stream().collect(Collectors.toMap(Subject::getId, subject -> subject));
        Map<Long, User> teacherList = repoUser.findAllById(idTeacherSet).stream().collect(Collectors.toMap(User::getId, user -> user));
        Map<Long, User> studentList = repoUser.findAllById(idStudentsSet).stream().collect(Collectors.toMap(User::getId, user -> user));

        return grades.stream().map(grade -> {
            GradeDTO gradeDTO = new GradeDTO();
            gradeDTO.setId(grade.getId());
            gradeDTO.setNote(grade.getNote());
            gradeDTO.setTeacher(teacherList.get(grade.getIdTeacher()));
            gradeDTO.setEstudent(studentList.get(grade.getIdStudent()));
            gradeDTO.setSubject(subjectList.get(grade.getIdSubject()));
            return gradeDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public List<Grade> getAll() {
        return repoNote.findAll();
    }

    @Override
    public Grade getById(Long id) {
        return repoNote.findById(id).orElse(null);
    }

    @Override
    public Grade create(Grade grade) {
        return repoNote.save(grade);
    }

    @Override
    public Grade update(Grade grade) {
        return repoNote.save(grade);
    }

    @Override
    public boolean delete(Long id) {
        if (repoNote.existsById(id)) {
            repoNote.deleteById(id);
            return true;
        }
        return false;
    }
}
