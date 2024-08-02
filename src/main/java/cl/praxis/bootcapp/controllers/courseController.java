package cl.praxis.bootcapp.controllers;

import cl.praxis.bootcapp.entities.Course;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/course")
public class courseController {

    // ---------- ROUTES ----------
    @GetMapping("/view")
    public String courseView() {
        return "course-view";
    }

    @GetMapping("/form")
    public String newPatient() {
        return "course-form";
    }

    // ---------- CRUD ---------
    @PostMapping("/patients")
    public String addPatient(@ModelAttribute Course course) {
        return "redirect:/course-view";
    }
    @PutMapping()
    public String updatePatient(@ModelAttribute Course course) {
        return "redirect:/course-view";
    }
}