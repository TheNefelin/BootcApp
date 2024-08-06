package cl.praxis.bootcapp.controllers;


import cl.praxis.bootcapp.entities.Role;
import cl.praxis.bootcapp.services.imp.RoleServiceImp;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class RoleController {

   private RoleServiceImp roleServiceImp;

    public RoleController(RoleServiceImp roleServiceImp) {
        this.roleServiceImp = roleServiceImp;
    }

    @GetMapping()
    public String getAllRoles(Model model) {
        List<Role> roles = roleServiceImp.getAll();
        model.addAttribute("roles", roles);
        return "role";
    }

}
