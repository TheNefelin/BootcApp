package cl.praxis.bootcapp.services.imp;

import cl.praxis.bootcapp.repositories.IGradeRepository;
import cl.praxis.bootcapp.repositories.ISubjectRepository;
import cl.praxis.bootcapp.repositories.IUserRepository;
import cl.praxis.bootcapp.services.IBaseServiceCRUD;
import cl.praxis.bootcapp.services.IGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class GradeService implements IGradeService, IBaseServiceCRUD<Grade> {
    @Autowired

    // MODIFICAR
    @Autowired
    private ISubjectRepository repoSubject;

    // MODIFICAR
    @Autowired
    private IUserRepository repoUser;

    @Override
    }

    @Override
    }

    @Override
        return toDTO(grades);
    }

    @Override
        return toDTO(grades);
    }

    @Override
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