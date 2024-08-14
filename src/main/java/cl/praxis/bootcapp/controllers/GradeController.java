package cl.praxis.bootcapp.controllers;

import cl.praxis.bootcapp.entities.Grade;


import cl.praxis.bootcapp.entities.GradeDTO;
import cl.praxis.bootcapp.entities.Subject;
import cl.praxis.bootcapp.entities.User;
import cl.praxis.bootcapp.services.IBaseServiceCRUD;
import cl.praxis.bootcapp.services.imp.GradeService;
import cl.praxis.bootcapp.services.imp.RoleServiceImp;
import cl.praxis.bootcapp.services.imp.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/grades")
public class GradeController {
    @Autowired
    private GradeService gradeService;

    @Autowired
    private IBaseServiceCRUD<User> userService;

    @Autowired
    private IBaseServiceCRUD<Subject> subjectService;

    @GetMapping
    public String getAllGrades(Model model) {
        List<GradeDTO> dto = gradeService.getAllGrades();
        model.addAttribute("grades", dto);
        return "grade_list";
    }

    @GetMapping("/create")
    public String createGradeRoute(Model model) {
        List<User> users = userService.getAll();
        List<Subject> subjects = subjectService.getAll();
        List<User> teachers = userService.getAll();

        model.addAttribute("users", users);
        model.addAttribute("teachers", teachers);
        model.addAttribute("subjects", subjects);
        return "grade_form";
    }

    @PutMapping("/update")
    public String routeUpdate(@RequestParam Long id, Model model) {
        Grade grade = gradeService.getById(id);
        List<User> users = userService.getAll();
        List<Subject> subjects = subjectService.getAll();

        if (grade != null) {
            List<User> teachers = users.stream()
                    .filter(user -> user.getRole().getId() == 2)
                    .collect(Collectors.toList());

            model.addAttribute("users", users);
            model.addAttribute("subjects", subjects);
            model.addAttribute("teachers", teachers);
            return "grade_form";
        } else
            return "redirect:/grade_list";
    }

    // -------------- CRUD --------------

    @PostMapping("/create")
    public String create(@ModelAttribute Grade grade) {
        gradeService.create(grade);
        return "redirect:/grade_list";
    }

    @PutMapping()
    public String update(@PathVariable Long id,
                         @ModelAttribute("grade") Grade grade) {
        grade.setId(id);
        gradeService.update(grade);
        return "redirect:/grades";
    }

    @DeleteMapping
    public String delete(@RequestParam Long id) {
        gradeService.delete(id);
        return "redirect:/grades";
    }
}

