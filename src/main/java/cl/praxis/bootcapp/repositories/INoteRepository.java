package cl.praxis.bootcapp.repositories;

import cl.praxis.bootcapp.entities.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface INoteRepository extends JpaRepository<Grade, Long> {
    List<Grade> findByIdTeacher(Long idTeacher);
    List<Grade> findByIdSubject(Long idSubject);
    List<Grade> findByIdStudent(Long idStudent);
}
