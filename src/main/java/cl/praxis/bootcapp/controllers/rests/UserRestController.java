package cl.praxis.bootcapp.controllers.rests;

import cl.praxis.bootcapp.entities.UserEntitiy;
import cl.praxis.bootcapp.services.IBaseServiceCRUD;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private IBaseServiceCRUD<UserEntitiy> userService;

    public UserRestController(IBaseServiceCRUD<UserEntitiy> userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public List<UserEntitiy> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserEntitiy getUserById(@PathVariable long id) {
        return userService.getById(id);
    }

    @PostMapping("/new")
    public UserEntitiy createUser(@RequestBody UserEntitiy user) {
        return userService.create(user);
    }

    @PutMapping("/update/{id}")
    public UserEntitiy updateUser(@RequestBody UserEntitiy user, @PathVariable long id) {

        System.out.println(user);
        UserEntitiy newUser = userService.getById(id);
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setSurname(user.getSurname());
        newUser.setRole(user.getRole());
        newUser.setCourses(null);
        return userService.update(newUser);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }

}
