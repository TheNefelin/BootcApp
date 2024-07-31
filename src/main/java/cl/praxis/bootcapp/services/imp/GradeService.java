package cl.praxis.bootcapp.services.imp;

import cl.praxis.bootcapp.entities.Grade;
import cl.praxis.bootcapp.entities.GradeDTO;
import cl.praxis.bootcapp.entities.Subject;
import cl.praxis.bootcapp.entities.User;
import cl.praxis.bootcapp.repositories.INoteRepository;
import cl.praxis.bootcapp.repositories.ISubjectRepository;
import cl.praxis.bootcapp.repositories.IUserRepository;
import cl.praxis.bootcapp.services.IGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GradeService implements IGradeService {
    @Autowired
    private INoteRepository repoGrade;

    @Autowired
    private ISubjectRepository repoSubject;

    @Autowired
    private IUserRepository repoUser;

    @Override
    public List<GradeDTO> getGradeByIdSubject(Long idSubject) {
        List<Grade> grades = repoGrade.findByIdSubject(idSubject);
        return toDTO(grades);
    }

    @Override
    public List<GradeDTO> getGradeByIdTeacher(Long idTeacher) {
        List<Grade> grades = repoGrade.findByIdTeacher(idTeacher);
        return toDTO(grades);
    }

    @Override
    public List<GradeDTO> getGradeByIdStudent(Long idStudent) {
        List<Grade> grades = repoGrade.findByIdStudent(idStudent);
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
            gradeDTO.setGrade(grade.getGrade());
            gradeDTO.setTeacher(teacherList.get(grade.getIdTeacher()));
            gradeDTO.setEstudent(studentList.get(grade.getIdStudent()));
            gradeDTO.setSubject(subjectList.get(grade.getIdSubject()));
            return gradeDTO;
        }).collect(Collectors.toList());
    }

}
