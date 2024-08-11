package cl.praxis.bootcapp.controllers;

import cl.praxis.bootcapp.entities.Subject;
import cl.praxis.bootcapp.services.imp.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping
    public String getAllSubject(Model model){
        model.addAttribute("subjects",subjectService.getAll());
        return "subject_list";
    }

    @GetMapping("/create")
    public String createSubjectRoute() {return "subject_form";}

    @PutMapping("/update")
    public String routeUpdate(@RequestParam Long id,Model model){
        Subject subject = subjectService.getById(id);

        if (subject != null) {
            model.addAttribute("subject", subject);
            return "subject_form";
        }
            else
                return "redirect:/subject_list";
    }
/////////////////CRUD///////////////////////
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
    public String delete(@RequestParam Long id) {
        subjectService.delete(id);
        return "redirect:/subjects";
    }

}


