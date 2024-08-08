package cl.praxis.bootcapp.controllers;

import cl.praxis.bootcapp.services.imp.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/grades")
public class GradeController {
    @Autowired
    private GradeService gradeService;

    // ROUTE
    @GetMapping
    public String getAllGrades(Model model) {
        model.addAttribute("grades", gradeService.getAllGrades());
        return "grade_list";
    }

    // ROUTE
    @GetMapping("/create")
    public String createGradeRoute(Model model) {
        model.addAttribute("estudiantes", gradeService);
        return "grade_form";
    }
}
