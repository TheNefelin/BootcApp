package cl.praxis.bootcapp.controllers;
import cl.praxis.bootcapp.entities.Course;
import cl.praxis.bootcapp.entities.Role;
import cl.praxis.bootcapp.entities.User;
import cl.praxis.bootcapp.services.IBaseServiceCRUD;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    private IBaseServiceCRUD<User> userCrudService;
    private IBaseServiceCRUD<Role> roleService;
    private IBaseServiceCRUD<Course> courseCrudService;

    public UserController(IBaseServiceCRUD<User> userCrudService, IBaseServiceCRUD<Course> courseCrudService,
                          IBaseServiceCRUD<Role> roleService) {
        this.userCrudService = userCrudService;
        this.courseCrudService = courseCrudService;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public String getAllUser(Model model) {
        List<User> users = userCrudService.getAll();
        model.addAttribute("users", users);
        return "user_list";
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
        userCrudService.create(user);
        return "redirect:/user/users";
    }

    // Ruta a formulario editar
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model){
        User user = userCrudService.getById(id);
        List<Role> roles = roleService.getAll();
        List<Course> courses = courseCrudService.getAll();
        model.addAttribute("user", user);
        model.addAttribute("roles", roles);
        model.addAttribute("courses", courses);
        model.addAttribute("numCourses", user.getCourses().size());
        return "/user_form";
    }

    @PutMapping("/edit/{id}")
    public String editUser(@PathVariable Long id,
                           @ModelAttribute("user") User user){
        user.setId(id);
        userCrudService.update(user);

        return "redirect:/user/users";
    }


    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id){
        userCrudService.delete(id);
        return "redirect:/user/users";
    }
}