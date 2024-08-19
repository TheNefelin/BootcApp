package cl.praxis.bootcapp.controllers.rests;

import cl.praxis.bootcapp.entities.UserEntity;
import cl.praxis.bootcapp.services.IBaseServiceCRUD;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private IBaseServiceCRUD<UserEntity> userService;

    public UserRestController(IBaseServiceCRUD<UserEntity> userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public List<UserEntity> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserEntity getUserById(@PathVariable long id) {
        return userService.getById(id);
    }

    @PostMapping("/new")
    public UserEntity createUser(@RequestBody UserEntity userEntity) {
        return userService.create(userEntity);
    }

    @PutMapping("/update/{id}")
    public UserEntity updateUser(@RequestBody UserEntity userEntity, @PathVariable long id) {

        System.out.println(userEntity);
        UserEntity newUserEntity = userService.getById(id);
        newUserEntity.setName(userEntity.getName());
        newUserEntity.setEmail(userEntity.getEmail());
        newUserEntity.setPassword(userEntity.getPassword());
        newUserEntity.setSurname(userEntity.getSurname());
        newUserEntity.setRole(userEntity.getRole());
        newUserEntity.setCourses(null);
        return userService.update(newUserEntity);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }

}
