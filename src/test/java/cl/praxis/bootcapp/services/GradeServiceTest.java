package cl.praxis.bootcapp.services;

import cl.praxis.bootcapp.entities.*;
import cl.praxis.bootcapp.repositories.IGradeRepository;
import cl.praxis.bootcapp.services.imp.GradeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
public class GradeServiceTest {
    private final static Logger LOG = LoggerFactory.getLogger(GradeServiceTest.class);
    public static Long id1, id2;
    public static int note1, note2;
    public static Grade grade1, grade2;
    public static GradeDTO gradeDTO1, gradeDTO2;

    @Mock
    IGradeRepository gradeRepository;

    @InjectMocks
    GradeService gradeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        LOG.info("--> Inicio SetUp GradeServiceTest");
        id1 = 1L;
        id2 = 2L;
        note1 = 7;
        note2 = 1;
        grade1 = new Grade(id1, note1, LocalDate.now(), 1L, 1L, 1L);
        grade2 = new Grade(id2, note2, LocalDate.now(), 2L, 2L, 2L);
        gradeDTO1 = new GradeDTO(grade1.getId(), grade1.getGrade(), new User(), new User(), new Subject());
        gradeDTO2 = new GradeDTO(grade2.getId(), grade2.getGrade(), new User(), new User(), new Subject());
    }

    //    @Test
//    public void getAllNoteByIdSubjectTest() {
//        Long id = 1L;
//        List<Grade> gradeDTOList = Arrays.asList(new Grade(), new Grade());
//
//        when(gradeRepo.findAllNotesByIdSubject(id)).thenReturn(gradeDTOList);
//
//        List<GradeDTO> dtoList = servicio.getAllNoteByIdSubject(id);
//
//        assertNotNull(dtoList);
//        assertEquals(2, dtoList.size());
//
//        verify(gradeRepo, times(1)).findAllNotesByIdSubject(id);
//    }

    @Test
    public void getAllGradesTest() {
        LOG.info("--> Inicio Test getAllGradesTest");
        when(gradeRepository.findAll()).thenReturn(List.of(grade1, grade2));

        LOG.warn("--> Inicio Pruebas Unitarias getAllGradesTest");
        List<Grade> gradesResult = gradeService.getAll();
        assertNotNull(gradesResult);
        assertEquals(gradesResult.size(), 2);

        LOG.warn("--> Verificacion de ejecucion de metodos de getAllGradesTest");
        verify(gradeRepository, times(1)).findAll();

        LOG.info("--> Finalizando test getAllGradesTest");
    }

    @Test
    public void getGradeByIdTest() {
        LOG.info("--> Inicio Test getGradeByIdTest");
        when(gradeRepository.findById(id1)).thenReturn(Optional.of(grade1));

        LOG.warn("--> Inicio Pruebas Unitarias getGradeByIdTest");
        Grade gradeResult = gradeService.getById(id1);
        assertNotNull(gradeResult);
        assertThat(gradeResult.getId()).isEqualTo(grade1.getId());

        LOG.warn("--> Verificacion de ejecucion de metodos de getGradeByIdTest");
        verify(gradeRepository, times(1)).findById(id1);

        LOG.info("--> Finalizando test getGradeByIdTest");
    }

    @Test
    public void saveGradeTest() {
        LOG.info("--> Inicio Test saveGradeTest");
        when(gradeRepository.save(grade1)).thenReturn(grade1);

        LOG.warn("--> Inicio Pruebas Unitarias saveGradeTest");
        Grade gradeResult = gradeService.create(grade1);
        assertNotNull(gradeResult);
        assertThat(gradeResult.getId()).isEqualTo(grade1.getId());
        assertThat(gradeResult.getGrade()).isEqualTo(grade1.getGrade());
        assertThat(gradeResult.getDate()).isEqualTo(grade1.getDate());
        assertThat(gradeResult.getIdStudent()).isEqualTo(grade1.getIdStudent());
        assertThat(gradeResult.getIdTeacher()).isEqualTo(grade1.getIdTeacher());
        assertThat(gradeResult.getIdSubject()).isEqualTo(grade1.getIdSubject());

        LOG.warn("--> Verificacion de ejecucion de metodos de saveGradeTest");
        verify(gradeRepository, times(1)).save(grade1);

        LOG.info("--> Finalizando test saveGradeTest");
    }

    @Test
    public void updateGradeTest() {
        LOG.info("--> Inicio Test updateGradeTest");
        grade1.setId(id2);
        grade1.setGrade(note2);
        when(gradeRepository.save(grade1)).thenReturn(grade1);

        LOG.warn("--> Inicio Pruebas Unitarias updateGradeTest");
        Grade gradeResult = gradeService.update(grade1);
        assertNotNull(gradeResult);
        assertEquals(gradeResult.getId(), id2);
        assertThat(gradeResult.getGrade()).isEqualTo(note2);

        LOG.warn("--> Verificacion de ejecucion de metodos de updateGradeTest");
        verify(gradeRepository, times(1)).save(grade1);

        LOG.info("--> Finalizando test updateGradeTest");
    }

    @Test
    public void deleteGradeTest() {
        LOG.info("--> Inicio Test deleteGradeTest");
        when(gradeRepository.existsById(id1)).thenReturn(true);

        LOG.warn("--> Inicio Pruebas Unitarias deleteGradeTest");
        Boolean idDeleted = gradeService.delete(id1);
        assertThat(idDeleted).isTrue();

        LOG.warn("--> Verificacion de ejecucion de metodos de deleteGradeTest");
        verify(gradeRepository, times(1)).existsById(id1);
        verify(gradeRepository, times(1)).deleteById(id1);

        LOG.info("--> Finalizando test deleteGradeTest");
    }
}
