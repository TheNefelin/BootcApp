package cl.praxis.bootcapp.services.imp;

import cl.praxis.bootcapp.entities.RolesEnum;
import cl.praxis.bootcapp.entities.dtos.AuthenticatedUserDTO;
import cl.praxis.bootcapp.entities.dtos.GradeDTO;
import cl.praxis.bootcapp.entities.Grade;
import cl.praxis.bootcapp.entities.Subject;
import cl.praxis.bootcapp.entities.UserEntitiy;
import cl.praxis.bootcapp.repositories.IGradeRepository;
import cl.praxis.bootcapp.repositories.ISubjectRepository;
import cl.praxis.bootcapp.repositories.IUserRepository;
import cl.praxis.bootcapp.security.CustomUserDetailsService;
import cl.praxis.bootcapp.services.IBaseServiceCRUD;

import cl.praxis.bootcapp.services.IGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GradeService implements IGradeService, IBaseServiceCRUD<Grade> {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private IGradeRepository repoGrade;

    @Autowired
    private ISubjectRepository repoSubject;

    @Autowired
    private IUserRepository repoUser;

    @Override
    public List<GradeDTO> getAllGradesByIdSubject(Long idSubject) {
        List<Grade> grades = repoGrade.findAllGradesByIdSubject(idSubject);
        return toDTO(grades);
    }

    @Override
    public List<GradeDTO> getAllGradesByIdTeacher(Long idTeacher) {
        List<Grade> grades = repoGrade.findAllGradesByIdTeacher(idTeacher);
        return toDTO(grades);
    }

    @Override
    public List<GradeDTO> getAllGradesByIdStudent(Long idStudent) {
        List<Grade> grades = repoGrade.findAllGradesByIdStudent(idStudent);
        return toDTO(grades);
    }

    @Override
    public List<GradeDTO> getAllGrades() {
        AuthenticatedUserDTO authUser = customUserDetailsService.getAuthenticatedUser();
        List<Grade> grades = new ArrayList<>();

        if (authUser.getRoles().contains(RolesEnum.ROLE_ADMIN.toString())) {
            grades = repoGrade.findAll();
        } else if (authUser.getRoles().contains(RolesEnum.ROLE_PROFESOR.toString())) {
            grades = repoGrade.findAllGradesByIdTeacher(authUser.getId());
        } else if (authUser.getRoles().contains(RolesEnum.ROLE_ESTUDIANTE.toString())) {
            grades = repoGrade.findAllGradesByIdStudent(authUser.getId());
        }

        return toDTO(grades);
    }

    @Override
    public GradeDTO getGradesByIdGrade(Long idNote) {
        Grade grade = repoGrade.findById(idNote).orElse(null);
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
        Map<Long, UserEntitiy> teacherList = repoUser.findAllById(idTeacherSet).stream().collect(Collectors.toMap(UserEntitiy::getId, user -> user));
        Map<Long, UserEntitiy> studentList = repoUser.findAllById(idStudentsSet).stream().collect(Collectors.toMap(UserEntitiy::getId, user -> user));

        return grades.stream().map(grade -> {
            GradeDTO gradeDTO = new GradeDTO();
            gradeDTO.setId(grade.getId());
            gradeDTO.setGrade(grade.getGrade());
            gradeDTO.setTeacher(teacherList.get(grade.getIdTeacher()));
            gradeDTO.setStudent(studentList.get(grade.getIdStudent()));
            gradeDTO.setSubject(subjectList.get(grade.getIdSubject()));
            return gradeDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public List<Grade> getAll() {
        return repoGrade.findAll();
    }

    @Override
    public Grade getById(Long id) {
        Grade grade = repoGrade.findById(id).orElse(null);
        return grade;
    }

    @Override
    public Grade create(Grade grade) {
        return repoGrade.save(grade);
    }

    @Override
    public Grade update(Grade grade) {
        return repoGrade.save(grade);
    }

    @Override
    public boolean delete(Long id) {
        if(repoGrade.existsById(id)){
            repoGrade.deleteById(id);
            return true;
        }
        return false;
    }
}
