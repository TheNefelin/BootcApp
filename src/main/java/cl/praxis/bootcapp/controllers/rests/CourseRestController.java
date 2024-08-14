package cl.praxis.bootcapp.controllers.rests;
import cl.praxis.bootcapp.entities.Course;
import cl.praxis.bootcapp.services.IBaseServiceCRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/course")
public class CourseRestController {

    @Autowired
    private IBaseServiceCRUD<Course> courseService;
    @GetMapping
    public ResponseEntity <List<Course>> getAllCourse (){
        List<Course> courses = courseService.getAll();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity <Course> getCourseById(@PathVariable Long id) {
        Course selectedCourse = courseService.getById(id);
        if(selectedCourse!= null){
            return new ResponseEntity<>(selectedCourse, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping
    public  ResponseEntity <Course> createCourse(@RequestBody Course course){
        Course newCourse = courseService.create(course);
        return new ResponseEntity<>(newCourse, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course course){
        course.setId(id);
        Course updatedCourse = courseService.update(course);
        if(updatedCourse!=null){
            return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Course> deleteCourse(@PathVariable Long id){
        boolean isDeleted = courseService.delete(id);
        if(isDeleted){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
