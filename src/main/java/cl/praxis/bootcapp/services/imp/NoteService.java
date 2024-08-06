package cl.praxis.bootcapp.services.imp;

import cl.praxis.bootcapp.entities.Grade;
import cl.praxis.bootcapp.entities.GradeDTO;
import cl.praxis.bootcapp.entities.Subject;
import cl.praxis.bootcapp.entities.User;
import cl.praxis.bootcapp.repositories.INoteRepository;
import cl.praxis.bootcapp.repositories.ISubjectRepository;
import cl.praxis.bootcapp.repositories.IUserRepository;
import cl.praxis.bootcapp.services.INoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class NoteService implements INoteService {
    @Autowired
    private INoteRepository repoNote;

    // MODIFICAR
    @Autowired
    private ISubjectRepository repoSubject;

    // MODIFICAR
    @Autowired
    private IUserRepository repoUser;

    @Override
    public List<GradeDTO> getNoteByIdSubject(Long idSubject) {
        List<Grade> grades = repoNote.findAllByIdSubject(idSubject);
        return toDTO(grades);
    }

    @Override
    public List<GradeDTO> getNoteByIdTeacher(Long idTeacher) {
        List<Grade> grades = repoNote.findAllByIdTeacher(idTeacher);
        return toDTO(grades);
    }

    @Override
    public List<GradeDTO> getNoteByIdStudent(Long idStudent) {
        List<Grade> grades = repoNote.findAllByIdStudent(idStudent);
        return toDTO(grades);
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
}
