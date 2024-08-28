package cl.praxis.bootcapp.services;
import cl.praxis.bootcapp.entities.Course;
import cl.praxis.bootcapp.repositories.ICourseRepository;
import cl.praxis.bootcapp.services.imp.CourseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CourseServiceTest {

    public static Course course1, course2;

    @Mock
    private ICourseRepository iCourseRepository;
    @InjectMocks
    private CourseServiceImpl courseService;
    @BeforeEach
    void setUp(){
        course1 = new Course(1L, "Introduccion a la programacion", true);
        course2 = new Course(2L,"Python",true);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAll(){
        //cuando se llame a metodo findAll() este retornara una array con los objetos de prueba
        when(iCourseRepository.findAll()).thenReturn(Arrays.asList(course1,course2));

        List<Course>result = courseService.getAll();
        //Verifica valor esperado vs valor (por metodo)
        assertEquals(2,result.size());
        assertEquals("Introduccion a la programacion",result.get(0).getName());
        assertEquals("Python",result.get(1).getName());
        //Verifica que se llamo al metodo findAll() 1 sola vez de la interfaz ICourseRepository
        verify(iCourseRepository, times(1)).findAll();
    }

    @Test
    void testGetById(){
        when(iCourseRepository.getById(1L)).thenReturn(course1);

        Course result = courseService.getById(1L);
        assertEquals(1L, result.getId());
        assertEquals("Introduccion a la programacion", result.getName());
        verify(iCourseRepository, times(1)).getById(1L);
    }
}
