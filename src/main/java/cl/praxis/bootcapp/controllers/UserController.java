package cl.praxis.bootcapp.controllers;
import cl.praxis.bootcapp.entities.Course;
import cl.praxis.bootcapp.entities.Role;
import cl.praxis.bootcapp.entities.User;
import cl.praxis.bootcapp.services.IBaseServiceCRUD;
import cl.praxis.bootcapp.services.imp.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller("users")
public class UserController {
    private IBaseServiceCRUD<User> crudService;
    private IBaseServiceCRUD<Role> roleService;
    private IBaseServiceCRUD<Course> courseCrudService;

    public UserController(IBaseServiceCRUD<User> crudService, IBaseServiceCRUD<Course> courseCrudService,
                          IBaseServiceCRUD<Role> roleService) {
        this.crudService = crudService;
        this.courseCrudService = courseCrudService;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public String getAllUser(Model model) {
        List<User> users = crudService.getAll();
        model.addAttribute("users", users);
        return "index";
    }

    // Ruta a formulario agregar
    @GetMapping("/new")
    public String showForm(@ModelAttribute User user, Model model) {
        List<Role> listRoles = roleService.getAll();
        List<Course> listCourse = courseCrudService.getAll();
        model.addAttribute("roles", listRoles );
        model.addAttribute("courses", listCourse);
        return "user_form";
    }


    @PostMapping("/new")
    public String insertUser(@ModelAttribute User user) {
        System.out.println("User" + user);
        crudService.create(user);
        return "redirect:/users";
    }

    // Ruta a formulario editar
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model){
        User user = crudService.getById(id);
        model.addAttribute("user", user);
        return "user_form";
    }

    @PutMapping("/edit/{id}")
    public String editUser(@PathVariable Long id,
                           @ModelAttribute("user") User user){
        user.setId(id);
        crudService.update(user);

        return "redirect:/users";
    }


    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id){
        crudService.delete(id);
        return "redirect:/users";
    }
}