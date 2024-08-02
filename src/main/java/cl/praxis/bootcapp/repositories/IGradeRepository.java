package cl.praxis.bootcapp.repositories;

import cl.praxis.bootcapp.entities.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IGradeRepository extends JpaRepository<Grade, Long> {
    List<Grade> findAllByIdTeacher(Long idTeacher);
    List<Grade> findAllByIdSubject(Long idSubject);
    List<Grade> findAllByIdStudent(Long idStudent);
}
