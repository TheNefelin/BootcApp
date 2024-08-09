package cl.praxis.bootcapp.repositories;

import cl.praxis.bootcapp.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISubjectRepository extends JpaRepository<Subject, Long> {
    Subject update(Subject subject);
}
