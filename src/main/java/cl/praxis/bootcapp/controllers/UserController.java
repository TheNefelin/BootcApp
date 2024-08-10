package cl.praxis.bootcapp.controllers;
import cl.praxis.bootcapp.entities.User;
import cl.praxis.bootcapp.services.imp.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller("UserController")
public class UserController {
    private UserServiceImpl crudService;

    public UserController(UserServiceImpl crudService) {
        this.crudService = crudService;
    }

    @GetMapping("/allusers")
    public String getAllUser(Model model) {
        List<User> users = crudService.getAll();
        model.addAttribute("users", users);
        return "index";
    }

    // Ruta a formulario
    @GetMapping("/new")
    public String showForm() {
        return "";
    }


    @PostMapping("/new")
    public String insertUser(@ModelAttribute User user) {
        User newUser = (User) crudService.create(user);
        return "redirect:/index";

    }


    @GetMapping("/edit")
    public String showEditForm(){
        return "";
    }

    @PutMapping("/edit/{id}")
    public String editUser(@PathVariable Long id,
                           @ModelAttribute("user") User newUser){
        User user = (User) crudService.getById(id);

        user.setEmail(newUser.getEmail());
        user.setPassword(newUser.getPassword());
        user.setName(newUser.getName());
        user.setSurname(newUser.getSurname());

        crudService.update(user);

        return "redirect:/index";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id){
        crudService.delete(id);
        return "redirect:/index";
    }
}