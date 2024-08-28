package cl.praxis.bootcapp.controllers;

import cl.praxis.bootcapp.entities.Grade;


import cl.praxis.bootcapp.entities.dtos.GradeDTO;
import cl.praxis.bootcapp.entities.Subject;
import cl.praxis.bootcapp.entities.UserEntitiy;
import cl.praxis.bootcapp.services.IBaseServiceCRUD;
import cl.praxis.bootcapp.services.imp.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/grades")
public class GradeController {
    @Autowired
    private GradeService gradeService;

    @Autowired
    private IBaseServiceCRUD<UserEntitiy> userService;

    @Autowired
    private IBaseServiceCRUD<Subject> subjectService;

    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESOR', 'ESTUDIANTE')")
    @GetMapping
    public String getAllGrades(Model model) {
        List<GradeDTO> dto = gradeService.getAllGrades();
        model.addAttribute("grades", dto);
        return "grade_list";
    }

    @GetMapping("/create")
    public String createGradeRoute(Model model) {
        List<UserEntitiy> student = userService.getAll();
        List<UserEntitiy> teachers = userService.getAll();
        List<Subject> subjects = subjectService.getAll();

        model.addAttribute("students", student);
        model.addAttribute("teachers", teachers);
        model.addAttribute("subjects", subjects);
        return "grade_form";
    }

    @PutMapping("/update")
    public String routeUpdate(@RequestParam Long id, Model model) {
        Grade grade = gradeService.getById(id);
        List<UserEntitiy> student = userService.getAll();
        List<UserEntitiy> teachers = userService.getAll();
        List<Subject> subjects = subjectService.getAll();

        if (grade != null) {
            model.addAttribute("students", student);
            model.addAttribute("teachers", teachers);
            model.addAttribute("subjects", subjects);
            model.addAttribute("grade", grade);
            return "grade_form";
        } else
            return "redirect:/grades";
    }

    // -------------- CRUD --------------

    @PostMapping()
    public String create(@ModelAttribute Grade grade) {
        grade.setDate(LocalDate.now());
        gradeService.create(grade);
        return "redirect:/grades";
    }

    @PutMapping()
    public String update(@ModelAttribute Grade grade) {
        Grade updateGrade = gradeService.getById(grade.getId());
        updateGrade.setDate(LocalDate.now());
        updateGrade.setGrade(grade.getGrade());

        gradeService.update(updateGrade);
        return "redirect:/grades";
    }

    @DeleteMapping
    public String delete(@RequestParam Long id) {
        gradeService.delete(id);
        return "redirect:/grades";
    }
}

