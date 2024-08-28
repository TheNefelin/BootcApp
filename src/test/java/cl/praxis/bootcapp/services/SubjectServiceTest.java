package cl.praxis.bootcapp.services;

import cl.praxis.bootcapp.entities.Subject;
import cl.praxis.bootcapp.repositories.ISubjectRepository;
import cl.praxis.bootcapp.services.imp.GradeService;
import cl.praxis.bootcapp.services.imp.SubjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
public class SubjectServiceTest {

    private final static Logger LOG = LoggerFactory.getLogger(SubjectServiceTest.class);
    public static Long id1, id2;
    public static String name1, name2;
    public static Subject subject1, subject2;

    @Mock
    GradeService gradeService;

    @Mock
    ISubjectRepository subjectRepository;

    @InjectMocks
    SubjectService subjectService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        LOG.info("--> Inicio SetUp");
        id1 = 1L;
        id2 = 2L;
        name1 = "T.I.";
        name2 = "Programacion";
        subject1 = new Subject(id1, name1, null);
        subject2 = new Subject(id2, name2, null);
    }

    @Test
    public void getAllSubjectsTest() {
        LOG.info("--> Inicio Test");
        when(subjectRepository.findAll()).thenReturn(List.of(subject1, subject2));

        LOG.warn("--> Inicio Pruebas Unitarias");
        List<Subject> subjectsResult = subjectService.getAll();
        assertNotNull(subjectsResult);
        assertEquals(2, subjectsResult.size());

        LOG.warn("--> Verificacion de ejecucion de metodos");
        verify(subjectRepository, times(1)).findAll();

        LOG.info("--> Finalizando test");
    }

    @Test
    public void getSubjectByIdTest() {
        LOG.info("--> Inicio Test");
        when(subjectRepository.findById(id1)).thenReturn(Optional.of(subject1));

        LOG.warn("--> Inicio Pruebas Unitarias");
        Subject subjectResult = subjectService.getById(id1);
        assertNotNull(subjectResult);
        assertThat(subjectResult.getId()).isEqualTo(subject1.getId());

        LOG.warn("--> Verificacion de ejecucion de metodos");
        verify(subjectRepository, times(1)).findById(id1);

        LOG.info("--> Finalizando test");
    }

    @Test
    public void saveSubjectTest() {
        LOG.info("--> Inicio Test");
        when(subjectRepository.save(subject1)).thenReturn(subject1);

        LOG.warn("--> Inicio Pruebas Unitarias");
        Subject subjectResult = subjectService.create(subject1);
        assertNotNull(subjectResult);
        assertThat(subjectResult.getId()).isEqualTo(subject1.getId());
        assertThat(subjectResult.getName()).isEqualTo(subject1.getName());

        LOG.warn("--> Verificacion de ejecucion de metodos");
        verify(subjectRepository, times(1)).save(subject1);

        LOG.info("--> Finalizando test");
    }

    @Test
    public void updateSubjectTest() {
        LOG.info("--> Inicio Test");
        subject1.setId(id2);
        subject1.setName(name2);
        when(subjectRepository.save(subject1)).thenReturn(subject1);

        LOG.warn("--> Inicio Pruebas Unitarias");
        Subject subjectResult = subjectService.update(subject1);
        assertNotNull(subjectResult);
        assertEquals(id2, subjectResult.getId());
        assertThat(subjectResult.getName()).isEqualTo(name2);

        LOG.warn("--> Verificacion de ejecucion de metodos");
        verify(subjectRepository, times(1)).save(subject1);

        LOG.info("--> Finalizando test");
    }

    @Test
    public void deleteSubjectTest() {
        LOG.info("--> Inicio Test");
        when(gradeService.getAllGradesByIdSubject(id1)).thenReturn(Collections.emptyList());

        LOG.warn("--> Inicio Pruebas Unitarias");
        Boolean idDeleted = subjectService.delete(id1);
        assertThat(idDeleted).isTrue();

        LOG.warn("--> Verificacion de ejecucion de metodos");
        verify(subjectRepository, times(1)).deleteById(id1);

        LOG.info("--> Finalizando test");
    }
}