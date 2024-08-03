package cl.praxis.bootcapp.Grade;

import cl.praxis.bootcapp.entities.Grade;
import cl.praxis.bootcapp.repositories.IGradeRepository;
import cl.praxis.bootcapp.services.imp.GradeService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class GradeServiceTest {

    @Mock
    private IGradeRepository gradeRepo;

    @InjectMocks
    private GradeService gradeService;

    @Test
    void getAll(){
        Grade grade = new Grade(1L, 7, LocalDate.of(2024,8,1), 1L, 1L, 1L);
        Grade grade2 = new Grade(2L, 5, LocalDate.of(2024,8,1), 2L, 2L, 1L);
        Grade grade3 = new Grade(3L, 4, LocalDate.of(2024,8,1), 3L, 3L, 1L);

        when(gradeRepo.findAll()).thenReturn(Arrays.asList(grade,grade2,grade3));

        List<Grade> grades = gradeService.getAll();

        assertEquals(3, grades.size());

    }

    @Test
    void getById() {
        Grade grade = new Grade(1L, 7, LocalDate.of(2024, 8, 1), 1L, 1L, 1L);

        when(gradeRepo.findById(grade.getId())).thenReturn(Optional.of(grade));

        Grade foundGrade = gradeService.getById(grade.getId());

        assertNotNull(foundGrade, "No null");
        assertEquals(grade.getId(), foundGrade.getId(), "id iguales");
    }

    @Test
    void create() {
        Grade grade = new Grade(1L, 7, LocalDate.of(2024, 8, 1), 1L, 1L, 1L);

        when(gradeRepo.save(grade)).thenReturn(grade);

        Grade savedGrade = gradeService.create(grade);

        assertNotNull(savedGrade, "No null");
        assertEquals(grade.getId(), savedGrade.getId(), "iguales");
    }

    @Test
    void update(){
        Grade grade = new Grade(1L, 7, LocalDate.of(2024, 8, 1), 1L, 1L, 1L);
        Grade updatedGrade = new Grade(1L, 5, LocalDate.of(2024, 8, 1), 1L, 1L, 1L);

        when(gradeRepo.findById(grade.getId())).thenReturn(Optional.of(grade));

        grade.setGrade(updatedGrade.getGrade());
        grade.setDate(updatedGrade.getDate());
        grade.setIdSubject(updatedGrade.getIdSubject());
        grade.setIdTeacher(updatedGrade.getIdTeacher());
        grade.setIdStudent(updatedGrade.getIdStudent());

        when(gradeRepo.save(grade)).thenReturn(grade);

        Grade result = gradeService.update(grade);

        assertNotNull(result, "No null");
        assertEquals(updatedGrade.getGrade(), result.getGrade(), "Updated");
    }

    void delete() {
        Grade grade = new Grade(1L, 7, LocalDate.of(2024, 8, 1), 1L, 1L, 1L);


        when(gradeRepo.existsById(grade.getId())).thenReturn(true);


        boolean result = gradeService.delete(grade.getId());


        verify(gradeRepo).deleteById(grade.getId());

        assertTrue(result, "borrado");
    }




}
