package cl.praxis.bootcapp.repositories;
import cl.praxis.bootcapp.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICourseRepository extends JpaRepository<Course, Long> {
    List<Course> findAllById(Iterable<Long> ids);

}
