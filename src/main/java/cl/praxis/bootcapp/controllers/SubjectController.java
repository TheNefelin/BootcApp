package cl.praxis.bootcapp.controllers;

import cl.praxis.bootcapp.entities.Course;
import cl.praxis.bootcapp.entities.Subject;
import cl.praxis.bootcapp.services.IBaseServiceCRUD;
import cl.praxis.bootcapp.services.imp.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private IBaseServiceCRUD<Course> courseService;

    // -------------- ROUTES --------------

    @GetMapping("/create")
    public String createSubjectRoute(Model model) {
        model.addAttribute("courses", courseService.getAll());
        return "subject_form";
    }

    @PutMapping("/update")
    public String UpdateSubjectRoute(@RequestParam Long id, Model model){
        Subject subject = subjectService.getById(id);
        List<Course> courses = courseService.getAll();

        if (subject != null) {
            model.addAttribute("courses", courses);
            model.addAttribute("subject", subject);
            return "subject_form";
        }
        else
            return "redirect:/subject_list";
    }

    // -------------- CRUD --------------
    @GetMapping
    public String getAllSubject(Model model){
        model.addAttribute("subjects",subjectService.getAll());
        return "subject_list";
    }

    @PostMapping()
    public String create(@ModelAttribute Subject subject) {
        subjectService.create(subject);
        return "redirect:/subject_list";
    }

    @PutMapping()
    public String update(@ModelAttribute Subject subject) {
        subjectService.update(subject);
        return "redirect:/subject_list";
    }

    @DeleteMapping
    public String delete(@RequestParam Long id, RedirectAttributes redirectAttributes) {
        if(subjectService.delete(id)) {
            redirectAttributes.addFlashAttribute("msgSuccess", "La asignatura ha sido eliminada correctamente");
        } else {
            redirectAttributes.addFlashAttribute("msgError", "No se ha eliminado la asignatura debido a que tiene al menos una nota asignada");
        }

        return "redirect:/subjects";
    }
}