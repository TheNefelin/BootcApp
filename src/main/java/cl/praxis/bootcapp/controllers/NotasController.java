package cl.praxis.bootcapp.controllers;

import cl.praxis.bootcapp.App;
import cl.praxis.bootcapp.entities.Grade;
import cl.praxis.bootcapp.services.IGradeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller()
@RequestMapping("/notas")
public class NotasController implements CommandLineRunner {
    private final static Logger LOG = LoggerFactory.getLogger(App.class);

    @Autowired
    private IGradeService gradeService;

    @GetMapping()
    public String notas(Model model){
        List<Grade> notas = gradeService.getGradeByIdStudent();
        model.addAttribute("notas", notas);
        return "notas";
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
