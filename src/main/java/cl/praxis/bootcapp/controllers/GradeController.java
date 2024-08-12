package cl.praxis.bootcapp.controllers;

import cl.praxis.bootcapp.entities.Grade;


import cl.praxis.bootcapp.services.imp.GradeService;
import cl.praxis.bootcapp.services.imp.RoleServiceImp;
import cl.praxis.bootcapp.services.imp.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/grades")
public class GradeController {
    @Autowired
    private GradeService gradeService;

    @GetMapping("/create")
    public String createGradeRoute() {
        return "grade_form";
    }

    @PutMapping("/update")
    public String routeUpdate(@RequestParam Long id, Model model) {
        Grade grade = gradeService.getById(id);

        if (grade != null) {
            model.addAttribute("grade", grade);
            return "grade_form";
        } else
            return "redirect:/grade_list";
    }

    // -------------- CRUD --------------

    @PostMapping()
    public String create(@ModelAttribute Grade grade) {
        gradeService.create(grade);
        return "redirect:/grade_list";
    }

    @PutMapping()
    public String update(@ModelAttribute Grade grade) {
        gradeService.update(grade);
        return "redirect:/grade_list";
    }

    @DeleteMapping
    public String delete(@RequestParam Long id) {
        gradeService.delete(id);
        return "redirect:/grade_list";
    }
}

