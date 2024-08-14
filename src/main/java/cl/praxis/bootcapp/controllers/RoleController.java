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

    @GetMapping("/allroles")
    public String getAllRoles(Model model) {
        List<Role> roles = roleServiceImp.getAll();
        model.addAttribute("roles", roles);
        return "role"; // Nombre de la vista que se renderiza (role.html)
    }

    @PostMapping("/roles")
    public String createRole(@ModelAttribute Role role) {
        roleServiceImp.create(role);
        return "redirect:/role/allroles"; // Redirige a la lista de roles después de crear o actualizar
    }

    @PutMapping("/roles/{id}")
    public String updateRole(@PathVariable Long id, @ModelAttribute Role role) {
        roleServiceImp.update(role);
        return "redirect:/allroles"; // Redirige a la lista de roles después de actualizar
    }

    @GetMapping("/roles/{id}")
    public String getRoleById(@PathVariable Long id, Model model) {
        Role role = roleServiceImp.getById(id);
        model.addAttribute("role", role);
        return "role"; // Renderiza la vista role.html con los detalles del rol
    }

    @DeleteMapping("/roles/{id}")
    public String deleteRole(@PathVariable Long id) {
        roleServiceImp.delete(id);
        return "redirect:/allroles"; // Redirige a la lista de roles después de eliminar
    }
}