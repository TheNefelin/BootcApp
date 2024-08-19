package cl.praxis.bootcapp.services.imp;
import cl.praxis.bootcapp.entities.Course;
import cl.praxis.bootcapp.repositories.ICourseRepository;
import cl.praxis.bootcapp.repositories.IUserRepository;
import cl.praxis.bootcapp.services.IBaseServiceCRUD;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements IBaseServiceCRUD<Course>{

   private ICourseRepository iCourseRepository;
   private IUserRepository iUserRepository;

   public CourseServiceImpl(ICourseRepository iCourseRepository, IUserRepository iUserRepository){
       this.iUserRepository = iUserRepository;
       this.iCourseRepository=iCourseRepository;
   }
    @Override
    public List<Course> getAll() {
        return iCourseRepository.findAll();
    }
    @Override
    public Course getById(Long id) {
        return iCourseRepository.getById(id);
    }

    public List<Course> getCoursesByIds(List<Long> courseId){
       return iCourseRepository.findAllById(courseId);
    }
    @Override
    public Course create(Course course) {
        return iCourseRepository.save(course);
    }
    @Override
    public Course update(Course course) {
      return iCourseRepository.save(course);
    }

    @Override
    public boolean delete(Long id) {
        iCourseRepository.deleteById(id);
        return false;
    }
}
