package cl.praxis.bootcapp.restControllers;

import cl.praxis.bootcapp.entities.User;
import cl.praxis.bootcapp.services.IBaseServiceCRUD;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private IBaseServiceCRUD<User> userService;

    public UserRestController(IBaseServiceCRUD<User> userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable long id) {
        return userService.getById(id);
    }

    @PostMapping("/new")
    public User createUser(@RequestBody User user) {
        return userService.create(user);
    }

    @PutMapping("/update/{id}")
    public User updateUser(@RequestBody User user, @PathVariable long id) {

        System.out.println(user);
        User newUser = userService.getById(id);
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setSurname(user.getSurname());
        newUser.setRole(user.getRole());
        newUser.setCourses(null);
        return userService.update(newUser);
    }
/*
    @DeleteMapping("")
    public void deleteUser(@RequestBody User user) {}

     */
}
