package cl.praxis.bootcapp.repositories;

import cl.praxis.bootcapp.entities.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IGradeRepository extends JpaRepository<Grade, Long> {
    List<Grade> findAllNotesByIdTeacher(Long idTeacher);
    List<Grade> findAllNotesByIdSubject(Long idSubject);
    List<Grade> findAllNotesByIdStudent(Long idStudent);
}