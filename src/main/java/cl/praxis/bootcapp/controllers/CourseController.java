package cl.praxis.bootcapp.controllers;
import cl.praxis.bootcapp.entities.Course;
import cl.praxis.bootcapp.services.imp.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Optional;

@Controller
public class CourseController  {
    @Autowired
    private CourseServiceImpl courseService;

    @GetMapping("/course/list")
    public String getAllCourse(Model model){
        model.addAttribute("courses", courseService.getAll());
        return "courseList";
    }
    @PostMapping("/course/edit/{id}")
    public String updateCourse(@PathVariable("id") Long id, Model model){
    model.addAttribute("editcourse", courseService);
    return "courseList";
    }
}
