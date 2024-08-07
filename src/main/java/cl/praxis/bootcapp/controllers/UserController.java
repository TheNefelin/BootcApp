package cl.praxis.bootcapp.controllers;

import cl.praxis.bootcapp.entities.User;
import cl.praxis.bootcapp.services.imp.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller("users")
public class UserController {
    private UserServiceImpl crudService;

    public UserController(UserServiceImpl crudService) {
        this.crudService = crudService;
    }

    @GetMapping("/users")
    public String getAllUser(Model model) {
       List<User> users = crudService.getAll();
        model.addAttribute("users", users);
        return "index";
    }

    // Ruta a formulario agregar
    @GetMapping("/new")
    public String showForm(@ModelAttribute User user) {
        return "demo";
    }


    @PostMapping("/new")
    public String insertUser(@ModelAttribute User user) {
        crudService.create(user);
        return "redirect:/users";

    }

    // Ruta a formulario editar
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model){
        User user = crudService.getById(id);
        model.addAttribute("user", user);
        return "demo";
    }

    @PutMapping("/edit/{id}")
    public String editUser(@RequestParam Long id,
                                @ModelAttribute("user") User user){
        user.setId(id);
        crudService.update(user);

        return "redirect:/users";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id){
        crudService.delete(id);
        return "redirect:/index";
    }
}
