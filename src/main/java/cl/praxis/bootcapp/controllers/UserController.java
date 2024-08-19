package cl.praxis.bootcapp.controllers;
import cl.praxis.bootcapp.entities.Course;
import cl.praxis.bootcapp.entities.Role;
import cl.praxis.bootcapp.entities.UserEntity;
import cl.praxis.bootcapp.services.IBaseServiceCRUD;
import cl.praxis.bootcapp.services.imp.CourseServiceImpl;
import cl.praxis.bootcapp.services.imp.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/user")
public class UserController {
    private IBaseServiceCRUD<UserEntity> userCrudService;
    private IBaseServiceCRUD<Role> roleService;
    private IBaseServiceCRUD<Course> courseCrudService;

    private CourseServiceImpl courseService ;


    public UserController(IBaseServiceCRUD<UserEntity> userCrudService, IBaseServiceCRUD<Course> courseCrudService,
                          IBaseServiceCRUD<Role> roleService, CourseServiceImpl courseService) {
        this.userCrudService = userCrudService;
        this.courseCrudService = courseCrudService;
        this.roleService = roleService;
        this.courseService = courseService;
    }

    @GetMapping("/users")
    public String getAllUser(Model model) {
        List<UserEntity> users = userCrudService.getAll();
        model.addAttribute("users", users);
        return "user_list";
    }

    // Ruta a formulario agregar
    @GetMapping("/new")
    public String showForm(@ModelAttribute UserEntity user, Model model) {
        List<Role> listRoles = roleService.getAll();
        List<Course> listCourse = courseCrudService.getAll();
        model.addAttribute("user", new UserEntity());
        model.addAttribute("roles", listRoles );
        model.addAttribute("courses", listCourse);
        return "user_form";
    }

    @GetMapping("/edit/userCourse/{id}")
    public String edituserCourse(@PathVariable Long id, Model model){
        UserEntity user = userCrudService.getById(id);
        List<Course> allCourse = courseCrudService.getAll();
        List<Course> courses = new ArrayList<>(user.getCourses());
        model.addAttribute("user", user);
        model.addAttribute("allCourses", allCourse);
        model.addAttribute("courses",courses);
        return "/user_course_update";
    }

    @PostMapping("/update/userCourse")
    public String updateUserCourse(@RequestParam("userId") Long userId, @RequestParam("courseIds") List<Long> courseIds) {
        UserEntity user = userCrudService.getById(userId);
        Set<Course> courses = new HashSet<>(courseService.getCoursesByIds(courseIds));
        user.setCourses(courses);
        userCrudService.update(user);
        return "redirect:/user/users";
    }
  
    @PostMapping
    public String insertUser(@ModelAttribute UserEntity user, @RequestParam String passwordConfirmation) {
        if (!user.getPassword().equals(passwordConfirmation)) {
            return "redirect:/register?error=passwordMismatch";
        }
        userCrudService.create(user);
        return "redirect:/user/users";
    }

    // Ruta a formulario editar
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model){
        UserEntity user = userCrudService.getById(id);
        List<Role> roles = roleService.getAll();
        List<Course> courses = courseCrudService.getAll();

        model.addAttribute("user", user);
        model.addAttribute("roles", roles);
        model.addAttribute("courses", courses);
        model.addAttribute("numCourses", user.getCourses().size());
        return "/user_form";
    }

    @PutMapping
    public String editUser(@ModelAttribute("user") UserEntity user){
        userCrudService.update(user);
        return "redirect:/user/users";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id){
        userCrudService.delete(id);
        return "redirect:/user/users";
    }

}