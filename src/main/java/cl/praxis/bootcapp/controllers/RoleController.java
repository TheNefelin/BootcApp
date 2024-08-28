package cl.praxis.bootcapp.controllers;


import cl.praxis.bootcapp.entities.Role;
import cl.praxis.bootcapp.services.imp.RoleServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    private final RoleServiceImp roleServiceImp;

    @Autowired
    public RoleController(RoleServiceImp roleServiceImp) {
        this.roleServiceImp = roleServiceImp;
    }

    @GetMapping()
    public String getAllRoles(Model model) {
        List<Role> roles = roleServiceImp.getAll();
        model.addAttribute("roles", roles);
        return "role";
    }

    @PostMapping()
    public String createRole(@ModelAttribute Role role) {
        roleServiceImp.create(role);
        return "redirect:/role";
    }

    @PostMapping("/update")
    public String updateRole(@ModelAttribute Role role, Model model) {
        roleServiceImp.update(role);
        model.addAttribute("roles", roleServiceImp.getAll());
        return "redirect:/role";
    }


    @GetMapping("/roles/{id}")
    public String getRoleById(@PathVariable Long id, Model model) {
        Role role = roleServiceImp.getById(id);
        model.addAttribute("role", role);
        return "role";
    }


    @DeleteMapping
    public String delete(@RequestParam Long id) {
        roleServiceImp.delete(id);
        return "redirect:/role";
    }
}