package cl.praxis.bootcapp.controllers;

import cl.praxis.bootcapp.entities.Grade;
import cl.praxis.bootcapp.entities.GradeDTO;
import cl.praxis.bootcapp.entities.User;
import cl.praxis.bootcapp.services.imp.GradeService;
import cl.praxis.bootcapp.services.imp.RoleServiceImp;
import cl.praxis.bootcapp.services.imp.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/grades")
public class GradeController {
    @Autowired
    private GradeService gradeService;

    @Autowired
    private UserServiceImpl userService;


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

    @GetMapping("/grades")
    public String getAllGradesByIdTeacher(@RequestParam Long id, Model model){
       User user = userService.getById(id);
       List<GradeDTO> gradeList;

       if(user.getRole().equals("Admin")){
           gradeList= gradeService.getAllGrades();

       }else if (user.getRole().equals("Profesor")){
           gradeList = gradeService.getAllGradesByIdTeacher(id);

       } else if (user.getRole().equals("Estudiante") || user.getRole().equals("Apoderado")){
           gradeList = gradeService.getAllGradesByIdStudent(id);

       } else {
           return "error404";
       }

       List<User> allUsers = userService.getAll();
       List<User> students = allUsers.stream()
               .filter(u -> u.getRole().equals("Estudiante"))
               .collect(Collectors.toList());




       model.addAttribute("role", user.getRole());
       model.addAttribute("grades", gradeList);
       model.addAttribute("students", students);

       return "grade_list";
    }
}
