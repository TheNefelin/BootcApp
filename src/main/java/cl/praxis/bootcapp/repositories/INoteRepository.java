package cl.praxis.bootcapp.repositories;

import cl.praxis.bootcapp.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface INoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByIdTeacher(Long idTeacher);
    List<Note> findByIdSubject(Long idSubject);
    List<Note> findByIdStudent(Long idStudent);
}
