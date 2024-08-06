package cl.praxis.bootcapp.controllers;


import cl.praxis.bootcapp.entities.Role;
import cl.praxis.bootcapp.repositories.IRoleRepository;
import cl.praxis.bootcapp.repositories.services.IBaseServiceCRUD;
import cl.praxis.bootcapp.repositories.services.imp.RoleServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class RolesController {

    @Autowired
    private IBaseServiceCRUD roleRepository;
    @Autowired
    private RoleServiceImp roleServiceImp;

    @GetMapping()
    public String getAllRoles(Model model) {
        List<Role> roles = roleServiceImp.getAll();
        model.addAttribute("roles", roles);
        return "role";
    }

}
