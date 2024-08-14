package cl.praxis.bootcapp.services;

import cl.praxis.bootcapp.entities.Course;
import cl.praxis.bootcapp.entities.Role;
import cl.praxis.bootcapp.entities.User;
import cl.praxis.bootcapp.repositories.IUserRepository;
import cl.praxis.bootcapp.services.imp.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private IUserRepository userRepo;

    @InjectMocks
    private UserServiceImpl userService;

    private final static Logger LOG = LoggerFactory.getLogger(SubjectServiceTest.class);
    private User user1;
    private User user2;
    private Course course1;
    private Course course2;
    private  Set<Course> courses = new HashSet<>();
    private Role role1;
    private Role role2;

    @BeforeEach
    void setUp(){
        //Datos simulados
        course1 = new Course(1L, "Introduccion a la programacion", true);
        course2 = new Course(2L,"Python",true);
        role1 = new Role(2L, "profesor", true);
        role2 = new Role(3L, "estudiante", true);

        courses.add(course1);
        courses.add(course2);

        user1 = new User(1L, "antomiranda@gmail.com", "1234", "Antonia", "Miranda", role1, courses);
        user2 = new User(2L, "arrtierrez@gmail.com", "123456", "Mario", "Cifuentes", role2, courses);
    }

    @Test
    void getAllUsersTest() {
        when(userRepo.findAll()).thenReturn(List.of(user1, user2));
        List<User> users = userService.getAll();
        assertEquals(2,users.size());
        assertEquals("Antonia", users.get(0).getName());
        assertEquals("Mario",users.get(1).getName());

        verify(userRepo, times(1)).findAll();
    }

    @Test
    void getByIdTest() {
        when(userRepo.findById(1L)).thenReturn(Optional.of(user1));

        User userById = userService.getById(1L);

        assertNotNull(userById);
        assertEquals(1L, userById.getId());
        verify(userRepo, times(1)).findById(1L);
    }

    @Test
    void createUserTest() {
        when(userRepo.save(user1)).thenReturn(user1);

        User createdUser = userService.create(user1);

        assertNotNull(createdUser);
        assertEquals(createdUser, user1);
        assertEquals(createdUser.getRole(), user1.getRole());
        verify(userRepo, times(1)).save(user1);
    }

    @Test
    void updateUserTest() {

        user1.setEmail("maryperez@email.com");
        user1.setPassword("marymary");
        user1.setName("María");
        user1.setSurname("Pérez");
        user1.setRole(role2);
        user1.setCourses(courses);

        User userExist = userService.getById(user1.getId());
        if(userExist != null) {
            User updatedUser = userService.update(user1);
            assertEquals("maryperez@email.com", updatedUser.getEmail());
            assertEquals("marymary", updatedUser.getPassword());
            assertEquals("María", updatedUser.getName());
            assertEquals("Pérez", updatedUser.getSurname());
            assertEquals("estudiante", updatedUser.getRole().getName());
            assertEquals(courses, updatedUser.getCourses());
            verify(userRepo, times(1)).save(user1);
        }
    }

    @Test
    void deleteUserTest () {
        boolean userDeleted = userService.delete(user2.getId());
        Assertions.assertTrue(userDeleted);
        verify(userRepo, times(1)).deleteById(user2.getId());
    }
}
