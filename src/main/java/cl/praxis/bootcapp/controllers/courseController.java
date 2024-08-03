package cl.praxis.bootcapp.controllers;

import cl.praxis.bootcapp.entities.Course;
import cl.praxis.bootcapp.services.IBaseServiceCRUD;
import cl.praxis.bootcapp.services.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class courseController {
    @Autowired
    private IBaseServiceCRUD<Course> courseService;

    // ---------- ROUTES ----------
    @GetMapping("/courses")
    public String courseView(Model model) {
        model.addAttribute("courses", courseService.getAll());
        return "course-view";
    }

    @GetMapping("/course-form")
    public String newCourse() {
        return "course-form";
    }

    // ---------- CRUD ---------
    @PostMapping("/add-course")
    public String addCourse(@ModelAttribute Course course) {
        courseService.create(course);
        return "redirect:/course-view";
    }
    @PutMapping("/update-course")
    public String updateCourse(@ModelAttribute Course course) {
        courseService.update(course);
        return "redirect:/course-view";
    }

    @GetMapping("/delete-course")
    public String deleteCourse(@RequestParam Long id) {
        courseService.delete(id);
        return "redirect:/course-view";
    }
}