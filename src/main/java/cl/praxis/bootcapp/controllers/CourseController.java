package cl.praxis.bootcapp.controllers;
import cl.praxis.bootcapp.entities.Course;
import cl.praxis.bootcapp.services.imp.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

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
  @PostMapping("/course/update")
    public String updateCourse(@ModelAttribute Course course, Model model){
        courseService.update(course);
        model.addAttribute("courses", courseService.getAll());
        return "redirect:/course/list";
    }
}
