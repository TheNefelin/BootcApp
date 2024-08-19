package cl.praxis.bootcapp.services;

import cl.praxis.bootcapp.entities.Course;
import cl.praxis.bootcapp.entities.Role;
import cl.praxis.bootcapp.entities.UserEntity;
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
public class UserEntityServiceTest {

    @Mock
    private IUserRepository userRepo;

    @InjectMocks
    private UserServiceImpl userService;

    private final static Logger LOG = LoggerFactory.getLogger(SubjectServiceTest.class);
    private UserEntity userEntity1;
    private UserEntity userEntity2;
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

        userEntity1 = new UserEntity(1L, "antomiranda@gmail.com", "1234", "Antonia", "Miranda", "1234",role1, courses);
        userEntity2 = new UserEntity(2L, "arrtierrez@gmail.com", "123456", "Mario", "Cifuentes", "123456",role2, courses);
    }

    @Test
    void getAllUsersTest() {
        when(userRepo.findAll()).thenReturn(List.of(userEntity1, userEntity2));
        List<UserEntity> userEntities = userService.getAll();
        assertEquals(2, userEntities.size());
        assertEquals("Antonia", userEntities.get(0).getName());
        assertEquals("Mario", userEntities.get(1).getName());

        verify(userRepo, times(1)).findAll();
    }

    @Test
    void getByIdTest() {
        when(userRepo.findById(1L)).thenReturn(Optional.of(userEntity1));

        UserEntity userEntityById = userService.getById(1L);

        assertNotNull(userEntityById);
        assertEquals(1L, userEntityById.getId());
        verify(userRepo, times(1)).findById(1L);
    }

    @Test
    void createUserTest() {
        when(userRepo.save(userEntity1)).thenReturn(userEntity1);

        UserEntity createdUserEntity = userService.create(userEntity1);

        assertNotNull(createdUserEntity);
        assertEquals(createdUserEntity, userEntity1);
        assertEquals(createdUserEntity.getRole(), userEntity1.getRole());
        verify(userRepo, times(1)).save(userEntity1);
    }

    @Test
    void updateUserTest() {

        userEntity1.setEmail("maryperez@email.com");
        userEntity1.setPassword("marymary");
        userEntity1.setName("María");
        userEntity1.setSurname("Pérez");
        userEntity1.setRole(role2);
        userEntity1.setCourses(courses);

        UserEntity userEntityExist = userService.getById(userEntity1.getId());
        if(userEntityExist != null) {
            UserEntity updatedUserEntity = userService.update(userEntity1);
            assertEquals("maryperez@email.com", updatedUserEntity.getEmail());
            assertEquals("marymary", updatedUserEntity.getPassword());
            assertEquals("María", updatedUserEntity.getName());
            assertEquals("Pérez", updatedUserEntity.getSurname());
            assertEquals("estudiante", updatedUserEntity.getRole().getName());
            assertEquals(courses, updatedUserEntity.getCourses());
            verify(userRepo, times(1)).save(userEntity1);
        }
    }

    @Test
    void deleteUserTest () {
        boolean userDeleted = userService.delete(userEntity2.getId());
        Assertions.assertTrue(userDeleted);
        verify(userRepo, times(1)).deleteById(userEntity2.getId());
    }
}
