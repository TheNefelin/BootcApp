package cl.praxis.bootcapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/")
public class indexController {

    @GetMapping()
    public String index() {
        return "index";
    }
}
